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
     * 服务器返回消息
     */
    private String message;
    /**
     * 服务器返回数据
     */
    private T result;

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
}
