package org.roof.im.connect.websocket;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import org.roof.im.connect.ConnectStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 使用本地缓存的 ConnectStore
 */
public class WebSocketSessionCacheConnectStore implements ConnectStore<WebSocketSession>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketSessionCacheConnectStore.class);
    private static final long DEFAULT_DURATION_MINUTES = 1000L;
    private Cache<String, WebSocketSession> cache;
    private long durationMinutes = DEFAULT_DURATION_MINUTES;

    @Override
    public void afterPropertiesSet() {
        if (cache == null) {
            synchronized (this) {
                if (cache != null) {
                    return;
                }
                cache = CacheBuilder.newBuilder().expireAfterWrite(durationMinutes, TimeUnit.MILLISECONDS)
                        .removalListener((RemovalListener<String, WebSocketSession>) notification -> {
                            LOGGER.debug("{} removal cause {}", notification.getKey(), notification.getCause().name());
                            try {
                                WebSocketSession webSocketSession = notification.getValue();
                                if (webSocketSession.isOpen()) {
                                    webSocketSession.close();
                                    LOGGER.debug("{} close success", notification.getKey());
                                }
                                LOGGER.debug("{} has been closed", notification.getKey());

                            } catch (IOException e) {
                                LOGGER.error(e.getMessage(), e);
                            }
                        }).build();
            }

        }

    }

    @Override
    public WebSocketSession get(String connectId) {
        return cache.getIfPresent(connectId);
    }

    @Override
    public void put(String connectId, WebSocketSession connect) {
        cache.put(connectId, connect);
    }

    @Override
    public boolean remove(String connectId) {
        cache.invalidate(connectId);
        return true;
    }

    public long getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(long durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
