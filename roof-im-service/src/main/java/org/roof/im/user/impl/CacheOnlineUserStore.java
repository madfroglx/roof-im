package org.roof.im.user.impl;

import org.roof.im.user.OnlineUserStore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;


/**
 * @author hzliuxin1
 * @since 2018/3/13 0013
 */
public class CacheOnlineUserStore implements OnlineUserStore, InitializingBean {

    private static final String USER_ATTRIBUTES_STORE = "userAttributesStore";


    //    private static final String ONLINE_USER_STORE = "OnlineUserStore";
//    private static final String ONLINE_USER_STORE_KEY = "OnlineUserStoreKey";
    private CacheManager cacheManager;
    private Cache cache;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheManager, "cacheManager cannot be null");
        cache = cacheManager.getCache(USER_ATTRIBUTES_STORE);
    }

    @Override
    public void add(String username) {
//        List<String> list = cache.get(ONLINE_USER_STORE_KEY, List.class);
//        list.add(username);
//        cache.put(ONLINE_USER_STORE_KEY, list);

    }

    @Override
    public boolean remove(String username) {
        return true;
//        List<String> list = cache.get(ONLINE_USER_STORE_KEY, List.class);
//        boolean result = list.remove(username);
//        cache.put(ONLINE_USER_STORE_KEY, list);
//        return result;
    }

    @Override
    public String[] get() {
//        Cache cache = cacheManager.getCache(ONLINE_USER_STORE_KEY);
        com.google.common.cache.Cache nativeCache = (com.google.common.cache.Cache) cache.getNativeCache();
//        List<String> list = cache.get(ONLINE_USER_STORE_KEY, List.class);
        Object[] keys = nativeCache.asMap().keySet().toArray();
        String[] result = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            Object key = keys[i];
            result[i] = key.toString();
        }
        return result;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
