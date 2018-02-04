package org.roof.im.converter;

import com.alibaba.fastjson.JSONObject;
import org.roof.im.request.Request;

/**
 * 消息转换，将JSON格式的消息转换为消息对象
 *
 * @author liuxin
 */
public interface RequestConverter<T extends Request> {
    /**
     * 是否支持该类型消息
     *
     * @param messageType 消息类型
     * @return 是否支持
     */
    boolean support(String messageType);

    /**
     * 将JSON格式的消息转换为消息对象
     *
     * @param jsonObjectMessage JSON类型的消息
     * @return 消息对象
     */
    T toMessage(JSONObject jsonObjectMessage);
}