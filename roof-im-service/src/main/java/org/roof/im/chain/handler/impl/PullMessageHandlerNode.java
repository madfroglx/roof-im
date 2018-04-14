package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.roof.im.message.Message;
import org.roof.im.message.MessageDao;
import org.roof.im.request.PullMessageRequest;
import org.roof.im.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * 拉取消息
 */
public class PullMessageHandlerNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(PullMessageHandlerNode.class);
    /**
     * 查询消息成功
     */
    private static final String QUERY_MESSAGE_SUCCESS = "queryMessageSuccess";
    /**
     * 查询消息失败
     */
    private static final String QUERY_MESSAGE_FAIL = "queryMessageFail";
    private static final int DEFAULT_LIMIT = 10;
    private MessageDao messageDao;

    public NodeResult<List<Message>> doNode(PullMessageRequest request, ValueStack valueStack) {
        List<Message> messages;
        try {
            messages = messageDao.query(UserService.joinUsername(request.getUsername(), request.getSender()),
                    request.getStartTime() == null ? 0 : request.getStartTime(),
                    request.getEndTime() == null ? 0 : request.getEndTime(),
                    request.getState(),
                    request.getOffset(), DEFAULT_LIMIT);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new NodeResult<>(QUERY_MESSAGE_FAIL);
        }
        NodeResult<List<Message>> nodeResult = new NodeResult<>(QUERY_MESSAGE_SUCCESS);
        nodeResult.setData(messages);
        return nodeResult;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}