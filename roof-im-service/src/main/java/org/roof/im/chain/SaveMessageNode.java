package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import org.roof.im.message.Message;
import org.roof.im.message.MessageDao;
import org.roof.im.request.MessageRequest;
import org.roof.im.session.Session;
import org.roof.im.user.UserService;

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
    private String source; //消息来源

    //TODO 保存sessionId
    public String doNode(MessageRequest request, Session effectiveSession, ValueStack valueStack) {
        if (request == null) {
            return MESSAGE_IS_NULL;
        }
        String receiver = request.getReceiver();
        String username = request.getUsername();
        Message message = new Message();
        message.setPayload(request.getPayload());
        message.setReceiver(receiver);
        message.setSender(username);
        message.setCreateTime(request.getCreateTime());
        message.setType(request.getType().name());
        message.setMessageKey(UserService.joinUsername(receiver, username));
        message.setSessionId(effectiveSession == null ? null : effectiveSession.getId());
        message.setSource(source);
        messageDao.save(message);
        valueStack.set(ImConstant.MESSAGE, message);
        return MESSAGE_SAVE_SUCCESS;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
