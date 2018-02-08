package org.roof.im.route.redis;

import org.roof.im.request.Request;
import org.roof.im.route.OfferIndexPolicy;
import org.roof.im.route.RequestRouter;
import org.roof.im.route.ServerNameBuilder;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liht
 */
public class RedisRequestRouter implements RequestRouter {
    /**
     * redis队列列表
     */
    private List<RedisList> lists;
    /**
     * 服务名称
     */
    private ServerNameBuilder serverNameBuilder;
    /**
     * offerIndexPolicy
     */
    private OfferIndexPolicy offerIndexPolicy;
    /**
     * retryTemplate
     */
    private RetryTemplate retryTemplate;
    /**
     * redis连接断开时间
     */
    private Long redisListOfferTimeout;

    @Override
    public boolean route(Request request) throws Throwable {
        String nodeName = serverNameBuilder.getName();

        retryTemplate.execute(new RetryCallback<Boolean, Throwable>() {
            @Override
            public Boolean doWithRetry(RetryContext retryContext) throws Throwable {
                int index = offerIndexPolicy.offerIndex(request, retryContext, lists.size());
                return lists.get(index).offer(request, redisListOfferTimeout, TimeUnit.MILLISECONDS);
            }
        });


        return false;
    }
}
