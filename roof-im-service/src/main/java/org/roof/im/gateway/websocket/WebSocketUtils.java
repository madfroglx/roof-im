package org.roof.im.gateway.websocket;

import org.slf4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author hzliuxin1
 * @since 2018/3/5 0005
 */
public class WebSocketUtils {

    public static void tryCloseWithError(WebSocketSession session, Throwable exception, Logger logger) {
        if (logger.isErrorEnabled()) {
            logger.error("Closing session due to exception for " + session, exception);
        }
        if (session.isOpen()) {
            try {
                session.close(CloseStatus.SERVER_ERROR);
            } catch (Throwable ex) {
                logger.error(ex.getMessage());
                // ignore
            }
        }
    }
}
