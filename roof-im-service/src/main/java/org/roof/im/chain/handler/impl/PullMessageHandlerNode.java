package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.roof.im.message.Message;
import org.roof.im.message.MessageDao;
import org.roof.im.request.PullMessageRequest;
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
    private static final int DEFAULT_LIMIT = 20;
    private MessageDao messageDao;

    public NodeResult<List<Message>> doNode(PullMessageRequest pullMessageRequest, ValueStack valueStack) {
        List<Message> messages;
        try {
            messages = messageDao.query(pullMessageRequest.getUsername(), pullMessageRequest.getSender(),
                    pullMessageRequest.getStartTime(),
                    pullMessageRequest.getEndTime(), pullMessageRequest.getOffset(), DEFAULT_LIMIT);
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