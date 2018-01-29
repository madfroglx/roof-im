package org.roof.im.user;

public class UserStatus {
    /**
     * 用户名
     */
    private String username;
    /**
     * 会话ID
     */
    private String sessionID;
    /**
     * 节点名称
     */
    private String nodeName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
