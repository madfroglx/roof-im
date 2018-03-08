package org.roof.im.request;

/**
 * 心跳请求
 *
 * @author liuxin
 * @date 2018/3/8
 */
public class HeartRequest extends Request {
    private String payload; //消息体

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}