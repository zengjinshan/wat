package com.fh.framewrok.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.ArrayDeque;

/**
 * Created by admin on 2017/5/16.
 */
public class MinaServerIoHandler extends IoHandlerAdapter {

    private ArrayDeque deque=new ArrayDeque();

    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg=message.toString();
        System.out.print(msg);
    }

}
