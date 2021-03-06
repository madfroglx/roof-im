package org.roof.im.response;

public class Response<T> {
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";
    /**
     * 状态
     */
    private String state = SUCCESS;
    /**
     * 异常编码
     */
    private String exceptionCode;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 服务器返回消息
     */
    private String message;
    /**
     * 服务器返回数据
     */
    private T result;
    /**
     * 序列
     */
    private String seq;

    public Response() {
    }

    public Response(String state) {
        this.state = state;
    }

    public Response(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public Response(T result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
