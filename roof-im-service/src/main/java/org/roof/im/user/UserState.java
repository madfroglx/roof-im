package org.roof.im.user;

public class UserState {
    /**
     * 用户名
     */
    private String username;
    /**
     * 会话ID
     */
    private String connectID;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 客户端类型
     */
    private String clientType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConnectID() {
        return connectID;
    }

    public void setConnectID(String connectID) {
        this.connectID = connectID;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
