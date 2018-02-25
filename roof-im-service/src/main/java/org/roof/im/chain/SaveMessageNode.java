package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import org.roof.im.message.Message;
import org.roof.im.message.MessageDao;
import org.roof.im.message.MessageUtils;
import org.roof.im.request.MessageRequest;

/**
 *  保存用户发送的信息
 */
public class SaveMessageNode {
    /**
     * 信息保存成功
     */
    private static final String MESSAGE_SAVE_SUCCESS = "messageSaveSuccess";
    /**
     * 信息为空
     */
    private static final String MESSAGE_IS_NULL = "messageIsNull";
    private MessageDao messageDao;

    public String doNode(MessageRequest request, ValueStack valueStack) {
        if (request == null) {
            return MESSAGE_IS_NULL;
        }
        Message message = MessageUtils.request2Message(request);
        messageDao.save(message);
        valueStack.set(ImConstant.MESSAGE, message);
        return MESSAGE_SAVE_SUCCESS;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}
