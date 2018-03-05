package org.roof.im.gateway;

/**
 * 连接不存在
 *
 * @author hzliuxin1
 * @since 2018/3/5 0005
 */
public class ConnectNotExistException extends Exception {
    public ConnectNotExistException() {
    }

    public ConnectNotExistException(String message) {
        super(message);
    }

    public ConnectNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectNotExistException(Throwable cause) {
        super(cause);
    }

    public ConnectNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
