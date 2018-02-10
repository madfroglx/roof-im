package org.roof.im.transport.impl;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import org.roof.im.transport.ServerNameBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获得本机ip地址作为服务名称
 *
 * @ 替换ip . 变为  _
 */
public class HostAddressServerNameBuilder implements ServerNameBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(HostAddressServerNameBuilder.class);
    private String serviceNamePrefix; //外部系统服务名称

    @Override
    public String getName() {
        String serviceName = "";
        try {
            serviceName = CharMatcher.anyOf(".").replaceFrom(InetAddress.getLocalHost().getHostAddress(), "_");
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage(), e);
        }
        Joiner joiner = Joiner.on("_").skipNulls();
        serviceName = joiner.join(serviceNamePrefix, serviceName);
        return serviceName;
    }

    public void setServiceNamePrefix(String serviceNamePrefix) {
        this.serviceNamePrefix = serviceNamePrefix;
    }
}
