package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.request.OpenSessionRequest;
import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;

public class OpenSessionHandlerNode extends AbstractRequestHandlerNode<OpenSessionRequest> {
    /**
     * 发起者不存在
     */
    private static final String SENDER_NOT_EXIST = "senderNotExist";
    /**
     * 接收者不存在
     */
    private static final String RECEIVER_NOT_EXIST = "receiverNotExist";
    /**
     * session 已经存在
     */
    private static final String SESSION_ALREADY_EXIST = "sessionAlreadyExist";
    /**
     * session 创建成功
     */
    private static final String OPEN_SESSION_SUCCESS = "openSessionSuccess";

    private SessionManager sessionManager;


    @Override
    public NodeResult<Session> doNode(OpenSessionRequest request, ValueStack valueStack) {
        String sender = request.getSender();
        String receiver = request.getReceiver();
        if (!userService.exist(sender)) {
            return new NodeResult<>(SENDER_NOT_EXIST);
        }
        if (!userService.exist(receiver)) {
            return new NodeResult<>(RECEIVER_NOT_EXIST);
        }
        //双方已存在session
        Session sessionExist = sessionManager.effective(sender, receiver);
        if (sessionExist != null) {
            NodeResult<Session> nodeResult = new NodeResult<>(SESSION_ALREADY_EXIST);
            nodeResult.setData(sessionExist);
            return nodeResult;
        }
        Session session = sessionManager.open(sender, receiver);
        NodeResult<Session> nodeResult = new NodeResult<>(OPEN_SESSION_SUCCESS);
        nodeResult.setData(session);
        return nodeResult;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
