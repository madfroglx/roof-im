package org.roof.im.transport.queue;

import org.roof.im.request.Request;
import org.roof.im.transport.ServerNameBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class LinkedBlockingQueueLoadBalanceMessagePublisher<E> extends AbstractBlockingQueueLoadBalanceMessagePublisher {
    private ServerNameBuilder serverNameBuilder;
    private BlockingQueue<E> queue;

    @Override
    protected void onInit() {
        String serverName = serverNameBuilder.getName();
        queuesMap = new HashMap();
        List<BlockingQueue<E>> queues = new ArrayList<>();
        queues.add(queue);
        queuesMap.put(serverName, queues);
    }

    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }

    public void setQueue(BlockingQueue<E> queue) {
        this.queue = queue;
    }
}
