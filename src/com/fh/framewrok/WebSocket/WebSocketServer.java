package com.fh.framewrok.WebSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 2017/11/4.
 */
public class WebSocketServer {

    private static final Logger logger= LoggerFactory.getLogger(WebSocketServer.class);

    public static final int WebSocket_Port=9090;

    public void run(int port) throws Exception{
        EventLoopGroup boosLoop=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        ServerBootstrap b=new ServerBootstrap();
        b.group(boosLoop,workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                ChannelPipeline pipeline=channel.pipeline();
                pipeline.addLast("http-codec",new HttpServerCodec());
                pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
                pipeline.addLast("http-chunked",new ChunkedWriteHandler());
                pipeline.addLast("handler",new WebSocketServerHandler());
            }
        });
    }
}
