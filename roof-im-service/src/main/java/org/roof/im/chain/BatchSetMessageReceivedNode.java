package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.roof.im.message.Message;
import org.roof.im.message.MessageDao;
import org.roof.im.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 批量设置消息已收取
 */
public class BatchSetMessageReceivedNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchSetMessageReceivedNode.class);
    /**
     * 消息设置为已收取成功
     */
    private static final String CHANGE_MESSAGE_STATE_SUCCESS = "changeMessageStateSuccess";
    /**
     * 消息设置为已收取失败
     */
    private static final String CHANGE_MESSAGE_STATE_ERROR = "changeMessageStateError";
    /**
     * 消息为空
     */
    private static final String MESSAGE_IS_NULL = "messageIsNull";

    private MessageDao messageDao;

    public NodeResult doNode(ValueStack valueStack) {
        NodeResult result = valueStack.getPreResult();
        if (result == null) {
            return new NodeResult(MESSAGE_IS_NULL);
        }
        Object r = result.getData();
        if (r == null) {
            return new NodeResult(CHANGE_MESSAGE_STATE_SUCCESS);
        }
        if (r instanceof List) {
            List<Message> messages = (List) r;
            if (messages.size() == 0) {
                return new NodeResult(CHANGE_MESSAGE_STATE_SUCCESS);
            }
            long[] ids = new long[messages.size()];
            for (int i = 0; i < messages.size(); i++) {
                Message message = messages.get(i);
                ids[i] = message.getId();
            }
            try {
                messageDao.updateStateBatch(ids, 1);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return new NodeResult(CHANGE_MESSAGE_STATE_ERROR);
            }
            NodeResult nr = new NodeResult(CHANGE_MESSAGE_STATE_SUCCESS);
            nr.setData(r);
            return nr;
        }
        return new NodeResult(MESSAGE_IS_NULL);
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}
