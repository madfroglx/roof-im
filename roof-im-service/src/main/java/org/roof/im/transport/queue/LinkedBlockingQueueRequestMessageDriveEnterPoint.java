package org.roof.im.transport.queue;

import org.roof.im.request.Request;

public class LinkedBlockingQueueRequestMessageDriveEnterPoint<E extends Request> extends AbstractBlockingQueueRequestMessageDriveEnterPoint {

    @Override
    protected void onInit() {
        //do nothing
    }
}
