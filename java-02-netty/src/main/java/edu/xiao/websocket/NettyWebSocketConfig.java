package edu.xiao.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.NettyRuntime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import io.netty.util.concurrent.Future;


@Slf4j
@Configuration
public class NettyWebSocketConfig {

    private EventLoopGroup masterGroup = new NioEventLoopGroup();
    private EventLoopGroup slaveGroup = new NioEventLoopGroup(NettyRuntime.availableProcessors());

    /**
     * 启动 websocket 服务器
     */
    @PostConstruct
    public void start() throws InterruptedException {
        run();
    }

    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        Future<?> masterFuture = masterGroup.shutdownGracefully();
        Future<?> slaveFuture = slaveGroup.shutdownGracefully();
        masterFuture.syncUninterruptibly();
        slaveFuture.syncUninterruptibly();
        log.info("关闭 websocket server 成功");
    }

    private void run() throws InterruptedException {
        // 服务器启动引导对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(masterGroup, slaveGroup)
                // 指定 Netty 通道类型
                .channel(NioServerSocketChannel.class)
                // .option(ChannelOption.SO_BACKLOG)
                // 为 masterGroup 添加日志处理器
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new NettyWebSocketChannelInitializer());
        // 启动服务器
        serverBootstrap.bind(9090).sync();
    }

}
