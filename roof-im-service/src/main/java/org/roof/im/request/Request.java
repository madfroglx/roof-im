package org.roof.im.request;

import javax.validation.constraints.NotEmpty;

public class Request {
    private String id;
    @NotEmpty
    private String connectId; // 连接ID
    @NotEmpty
    private String requestType; //请求类型
    @NotEmpty
    private String token; // 授权token
    private String username; //用户名
    @NotEmpty
    private String clientType; //客户端类型
    private long createTime;  //创建时间
    private String serverName; //服务器名称
    private String seq; //序列

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConnectId() {
        return connectId;
    }

    public void setConnectId(String connectId) {
        this.connectId = connectId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
