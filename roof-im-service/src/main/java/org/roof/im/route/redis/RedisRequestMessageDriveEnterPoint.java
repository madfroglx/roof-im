package org.roof.im.route.redis;

import org.roof.im.request.Request;
import org.roof.im.route.RequestMessageDriveEnterPoint;
import org.roof.im.route.ServerNameBuilder;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.support.collections.RedisList;

import java.util.List;
import java.util.concurrent.Future;

/**
 * org.roof.im.route.redis
 * <p>
 * 使用Hystrix异步读取redis队列中的数据，执行对应逻辑操作
 * <p>
 * 本方法根据RedisList的数量进行
 *
 * @author liht
 * @date 18/2/7
 */
public class RedisRequestMessageDriveEnterPoint implements RequestMessageDriveEnterPoint {
    private List<RedisList> lists;

    private ServerNameBuilder serverNameBuilder;

    @Override
    public void receive() {
        //String serverName = serverNameBuilder.getName();
        //每个redis队列创建一个命令，异步去对应redis取，
        for (int i = 0; i < lists.size(); i++) {
            RedisList redisList = lists.get(i);
            Future<Request> futrue = new CommandGetRedisListData("redisList" + i, redisList).queue();
        }
    }
}
