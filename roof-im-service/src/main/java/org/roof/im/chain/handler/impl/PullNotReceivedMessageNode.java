package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.roof.im.message.Message;
import org.roof.im.message.MessageDao;
import org.roof.im.request.PullNotReceivedMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 拉取未读消息
 */
public class PullNotReceivedMessageNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(PullNotReceivedMessageNode.class);
    /**
     * 未拉取消息查询失败
     */
    private static final String NOT_RECEIVED_MESSAGE_QUERY_ERROR = "notReceivedMessageQueryError";
    /**
     * 未拉取消息查询成功
     */
    private static final String NOT_RECEIVED_MESSAGE_QUERY_SUCCESS = "notReceivedMessageQuerySuccess";

    private MessageDao messageDao;

    public NodeResult<List<Message>> doNode(PullNotReceivedMessageRequest request, ValueStack valueStack) {
        try {
            List<Message> messages = messageDao.queryNotReceived(request.getUsername(), request.getSender(), 20);
            NodeResult<List<Message>> result = new NodeResult<>(NOT_RECEIVED_MESSAGE_QUERY_SUCCESS);
            result.setData(messages);
            return result;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new NodeResult<>(NOT_RECEIVED_MESSAGE_QUERY_ERROR);
        }
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}
