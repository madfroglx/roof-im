package org.roof.im.connect.websocket;

import org.roof.im.connect.ConnectStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;
import org.springframework.web.socket.WebSocketSession;

/**
 * 使用本地缓存的 ConnectStore
 */
public class WebSocketSessionCacheConnectStore implements ConnectStore<WebSocketSession>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketSessionCacheConnectStore.class);
    public static final String CONNECT_STORE = "ConnectStore";
    private Cache cache;
    private CacheManager cacheManager;

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(cacheManager, "cacheManager cannot be null");
        cache = cacheManager.getCache(CONNECT_STORE);
    }

    @Override
    public WebSocketSession get(String connectId) {
        return cache.get(connectId, WebSocketSession.class);
    }

    @Override
    public void put(String connectId, WebSocketSession connect) {
        cache.put(connectId, connect);
    }

    @Override
    public boolean remove(String connectId) {
        cache.evict(connectId);
        return true;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
