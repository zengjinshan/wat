package com.fh.framewrok.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by admin on 2017/5/16.
 */
public class TimeClientHander extends IoHandlerAdapter {
   /* @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        System.out.println("client与:"+ioSession.getRemoteAddress().toString()+"建立连接");
    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
        System.out.println("打开连接");
    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {
        System.out.println("client与:"+ioSession.getRemoteAddress().toString()+"断开连接");
    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
        System.out.println( "IDLE " + ioSession.getIdleCount( idleStatus ));
    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {
        throwable.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession ioSession, Object o) throws Exception {
        System.out.println("client接受信息:"+o.toString());
    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {
        System.out.println("client发送信息"+o.toString());
    }

    @Override
    public void inputClosed(IoSession ioSession) throws Exception {

    }*/

    public void messageReceived(IoSession session, Object message) throws Exception {
        String  msg = message.toString();
        System.out.println("客户端收到服务器返回的消息" + msg);
    }
}
