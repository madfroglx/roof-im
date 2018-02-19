package org.roof.im.user;

public class UserState {
    /**
     * 用户名
     */
    private String username;
    /**
     * 会话ID
     */
    private String connectId;
    /**
     * 节点名称
     */
    private String serverName;
    /**
     * 客户端类型
     */
    private String clientType;
    /**
     * 上线时间
     */
    private long updateTime;

    /**
     * 状态
     * {@link org.roof.im.chain.ImConstant#ONLINE}
     * {@link org.roof.im.chain.ImConstant#HIDE}
     * {@link org.roof.im.chain.ImConstant#OFFLINE}
     */
    private String state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConnectId() {
        return connectId;
    }

    public void setConnectId(String connectId) {
        this.connectId = connectId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
