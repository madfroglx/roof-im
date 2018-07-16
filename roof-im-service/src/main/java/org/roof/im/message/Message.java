package org.roof.im.message;

import org.apache.ibatis.type.Alias;

/**
 * 消息
 */
@Alias("Message")
public class Message {
    /**
     * id
     */
    private Long id;
    /**
     * 发送者
     */
    private String sender;
    /**
     * 接收者
     */
    private String receiver;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息内容
     */
    private String payload;
    /**
     * 发送时间
     */
    private long createTime;
    /**
     * 状态 0:未收取, 1:已收取
     */
    private int state;
    /**
     * 客户端类型
     *
     * @see {@link org.roof.im.request.ClientType}
     */
    private String clientType;
    /**
     * 聊天双方用户名组合
     * {@link org.roof.im.user.UserService#joinUsername(String, String)}
     */
    private String messageKey;

    private Long sessionId;
    /**
     * 请求类型（系统消息，等等）
     */
    private String requestType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }
}
