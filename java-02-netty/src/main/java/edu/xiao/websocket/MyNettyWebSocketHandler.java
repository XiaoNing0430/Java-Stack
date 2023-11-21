package edu.xiao.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class MyNettyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 有新的连接建立时调用此方法
     *
     * @param ctx 上下文处理器
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("新的客户端建立了连接：{}", objectMapper.writeValueAsString(ctx));
    }

    /**
     * 有新消息时调用此方法
     *
     * @param ctx 上下文处理器
     * @param msg 消息
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("客户端发来的消息：{}", msg.text());
        ctx.channel().write(new TextWebSocketFrame("服务端收到消息了"));
        ctx.flush();
    }

    /**
     * 非活跃状态，没有连接远程主机的时候，调用此方法
     *
     * @param ctx 上下文处理器
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("客户端不活跃，{}", objectMapper.writeValueAsString(ctx));
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("handlerRemoved，{}", objectMapper.writeValueAsString(ctx));
    }
}
