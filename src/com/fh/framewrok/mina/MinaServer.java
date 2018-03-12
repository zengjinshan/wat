package com.fh.framewrok.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/5/16.
 */
public class MinaServer {

    public static final String HOST="127.0.0.1";

    private static final int PORT=10061;

    private static final int BUFFER_SIZE=1024*1024;

    private static final ThreadFactory THREAD_FACTORY=new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(null,r,"msgReceiveThread",1024*1024);
        }
    };

    public static void main(String[] args){
        try {
        NioSocketAcceptor acceptor=new NioSocketAcceptor(Runtime.getRuntime().availableProcessors()+1);

        acceptor.getSessionConfig().setReceiveBufferSize(BUFFER_SIZE);

        OrderedThreadPoolExecutor executor=new OrderedThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*4,
                Runtime.getRuntime().availableProcessors()*24+1,300, TimeUnit.SECONDS,THREAD_FACTORY);

        acceptor.getFilterChain().addLast("threadPool",new ExecutorFilter(executor));

        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("utf-8"))));

        acceptor.setHandler(new MinaServerIoHandler());

        acceptor.bind(new InetSocketAddress(HOST,PORT));
            Map<Long, IoSession> managedSessions = acceptor.getManagedSessions();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
