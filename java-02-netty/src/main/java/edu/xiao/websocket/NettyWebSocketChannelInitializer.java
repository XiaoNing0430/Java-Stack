package edu.xiao.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 初始化管道
 */
public class NettyWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 获取对应管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 因为使用http协议，所以需要使用http的编码器，解码器
        pipeline.addLast(new HttpServerCodec());
        // 以块方式写，添加 chunkedWriter 处理器。添加对大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        /**
         * 说明：
         *  1. http数据在传输过程中是分段的，HttpObjectAggregator可以把多个段聚合起来；
         *  2. 这就是为什么当浏览器发送大量数据时，就会发出多次 http请求的原因
         */
        pipeline.addLast(new HttpObjectAggregator(8 * 1024));
        /**
         * 设置websocket连接前缀前缀
         * 说明：
         *  1. 对于 WebSocket，它的数据是以帧frame 的形式传递的；
         *  2. 可以看到 WebSocketFrame 下面有6个子类
         *  3. 浏览器发送请求时： ws://localhost:7000/hello 表示请求的uri
         *  4. WebSocketServerProtocolHandler 核心功能是把 http协议升级为 ws 协议，保持长连接；
         *      是通过一个状态码 101 来切换的
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
        pipeline.addLast(new MyNettyWebSocketHandler());
    }
}
