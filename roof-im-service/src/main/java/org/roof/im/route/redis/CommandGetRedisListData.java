package org.roof.im.route.redis;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.roof.im.request.MessageRequest;
import org.roof.im.request.OfflineRequest;
import org.roof.im.request.OnlineRequest;
import org.roof.im.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.support.collections.RedisList;

import java.util.concurrent.TimeUnit;

/**
 * org.roof.im.route.redis
 * <p>
 *
 * @author liht
 * @date 18/2/8
 */
public class CommandGetRedisListData extends HystrixCommand<Request> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandGetRedisListData.class);

    private RedisList<Request> redisList;
    private String groupName;

    protected CommandGetRedisListData(String groupName, RedisList redisList) {
        super(
            /* 使用HystrixCommandGroupKey工厂定义 */
            Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupName))
                /* 配置依赖超时时间,1秒 */
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000))
                /*  使用HystrixThreadPoolKey工厂定义线程池名称 */
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey" + groupName))
        );
        this.redisList = redisList;
        this.groupName = groupName;
    }

    @Override
    protected Request run() throws Exception {
        Request request = this.redisList.poll(100, TimeUnit.MILLISECONDS);

        if (request instanceof MessageRequest) {
            LOGGER.debug("do MessageRequest chain");
        } else if (request instanceof OfflineRequest) {
            LOGGER.debug("do OfflineRequest chain");
        } else if (request instanceof OnlineRequest) {
            LOGGER.debug("do OnlineRequest chain");
        }

        return request;
    }

    @Override
    protected Request getFallback() {
        return super.getFallback();
    }
}
