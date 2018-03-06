package org.roof.im.transport.redis;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器名称获取工厂类
 *
 * @author hzliuxin1
 * @since 2018/3/6 0006
 */
public class ServerNameFactoryBean implements FactoryBean<List<String>>, InitializingBean {

    private List<String> servers;
    private String serviceNamePrefix; //外部系统服务名称

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(servers, "servers cannot null");
        Assert.notNull(serviceNamePrefix, "serviceNamePrefix cannot null");
    }


    @Override
    public List<String> getObject() throws Exception {
        List<String> result = new ArrayList<>(servers.size());
        for (String server : servers) {
            result.add(serviceNamePrefix + server);
        }
        return result;
    }

    @Override
    public Class<?> getObjectType() {
        return List.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }


}
