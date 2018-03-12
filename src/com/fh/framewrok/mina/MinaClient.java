package com.fh.framewrok.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by admin on 2017/5/16.
 */
public class MinaClient {

    public static final String HOST="127.0.0.1";

    public static final int PORT=10061;

    public static void main(String[] args) {
        IoSession session=null;
        IoConnector connector = new NioSocketConnector();
        try{
            connector.setConnectTimeoutMillis(30000L);
            connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
            connector.setHandler(new TimeClientHander());
            ConnectFuture connectFuture=connector.connect(new InetSocketAddress(HOST,PORT));
            connectFuture.awaitUninterruptibly();
            session = connectFuture.getSession();
            Scanner sc=new Scanner(System.in);
            boolean b=false;
            while (!b){
                String str=sc.next();
                if(str.equals("quit")){
                    b=true;
                }
                session.write(str);
            }

            System.out.println("客户端向服务器发送消息" + "jinshan");;
        }catch (Exception e){
            e.printStackTrace();
        }
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
        if(session!=null){
            if(session.isConnected()){
                session.getCloseFuture().awaitUninterruptibly();
            }
            //释放资源.
            connector.dispose();
        }
    }
}
