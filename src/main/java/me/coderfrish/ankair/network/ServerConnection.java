package me.coderfrish.ankair.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.coderfrish.ankair.MinecraftServer;
import me.coderfrish.ankair.core.IManager;

public class ServerConnection implements IManager {
    private final MinecraftServer server = MinecraftServer.getServer();
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final ServerBootstrap bootstrap;

    private ChannelFuture future;

    public ServerConnection() {
        this.bossGroup = getEventLoopGroup();
        this.workerGroup = getEventLoopGroup();

        this.bootstrap = new ServerBootstrap();
        this.initManager();
    }

    @Override
    public void initManager() {
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.localAddress(server.getAddress());

        /* setting channel */
        bootstrap.channel(this.getChannelClazz());
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.IP_TOS, 0x18);
    }

    @Override
    public void runManager() {
        bootstrap.childHandler(channelHandler);
        this.future = bootstrap.bind();
    }

    @Override
    public void stopManager() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

        future.channel().closeFuture().syncUninterruptibly();
    }

    public EventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    public ChannelFuture getFuture() {
        return future;
    }

    private final ChannelHandler channelHandler = new ChannelInitializer<Channel>() {
        @Override
        @SuppressWarnings("unused")
        protected void initChannel(Channel channel) {
            ChannelPipeline pipeline = channel.pipeline();

            /* length-codec */
            pipeline.addLast(new LengthDecoder());
            pipeline.addLast(new LengthEncoder());

            /* packet-codec */
            pipeline.addLast(new PacketDecoder());
            pipeline.addLast(new PacketEncoder());

            /* handler */
            pipeline.addLast(new ClientConnection());
        }
    };

    private EventLoopGroup getEventLoopGroup() {
        return Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
    }

    private Class<? extends ServerChannel> getChannelClazz() {
        return Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
    }
}
