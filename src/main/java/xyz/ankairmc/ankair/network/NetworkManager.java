package xyz.ankairmc.ankair.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import xyz.ankairmc.ankair.player.Player;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;

public class NetworkManager {
    public static final AttributeKey<Player> session = AttributeKey.valueOf("session");
    public static final EventLoopGroup bossEventLoop = getEventLoop();
    public static final EventLoopGroup workerEventLoop = getEventLoop();
    private final ServerBootstrap server = new ServerBootstrap();
    private final InetSocketAddress address;
    private ChannelFuture future;
    private boolean inited = false;

    public NetworkManager(InetSocketAddress address) {
        this.address = address;
        this.initServer();
    }

    private void initServer() {
        server.group(bossEventLoop, workerEventLoop);
        server.channel(this.getServerChannel());
        server.childOption(ChannelOption.IP_TOS, 0x18);
        server.childOption(ChannelOption.TCP_NODELAY, true);
        server.childOption(ChannelOption.SO_KEEPALIVE, true);
        server.localAddress(address);

        inited = true;
    }

    public void runServer() {
        if (!inited) return;

        this.server.childHandler(NetworkPipeline.getInstance());
        this.future = server.bind();
    }

    public void stopServer() {
        if (!inited) return;

        workerEventLoop.shutdownGracefully();
        bossEventLoop.shutdownGracefully();

        this.future.channel().closeFuture().syncUninterruptibly();
    }

    public static EventLoopGroup getEventLoop() {
        ThreadFactory threadFactory = (r) -> {
            Thread thread = new Thread(r);
            thread.setName(
                    String.format(
                            "Netty %s", thread.threadId()
                    )
            );

            return thread;
        };

        return Epoll.isAvailable() ? new EpollEventLoopGroup(threadFactory) : new NioEventLoopGroup(threadFactory);
    }

    public Class<? extends ServerChannel> getServerChannel() {
        return Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
    }
}
