package org.roof.im.chain;

import org.roof.im.message.Message;
import org.roof.im.message.MessageDao;
import org.roof.im.response.Response;

/**
 * 设置消息为已被收取
 */
public class SetMessageTakedNode {
    /**
     * 消息设置为已收取成功
     */
    private static final String CHANGE_MESSAGE_STATE_SUCCESS = "changeMessageStateSuccess";
    /**
     * 消息为空
     */
    private static final String MESSAGE_IS_NULL = "messageIsNull";
    /**
     * 消息ID不存在
     */
    private static final String MESSAGE_ID_NOT_EXIST = "messageIdNotExist";
    private MessageDao messageDao;

    public String doNode(Response response) {
        if (response == null) {
            return MESSAGE_IS_NULL;
        }
        Object result = response.getResult();
        Message message = null;
        if (result instanceof Message) {
            message = (Message) result;
        }
        if (message == null) {
            return MESSAGE_IS_NULL;
        }
        if (message.getId() == null || message.getId() == 0) {
            return MESSAGE_ID_NOT_EXIST;
        }
        int affectedRows = messageDao.updateState(message.getId(), 1);
        if (affectedRows == 0) {
            return MESSAGE_ID_NOT_EXIST;
        }
        return CHANGE_MESSAGE_STATE_SUCCESS;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}