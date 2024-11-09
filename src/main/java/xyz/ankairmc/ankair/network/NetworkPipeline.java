package xyz.ankairmc.ankair.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import xyz.ankairmc.ankair.player.Player;

public class NetworkPipeline extends ChannelInitializer<Channel> {
    private static NetworkPipeline instance;

    public static NetworkPipeline getInstance() {
        synchronized (NetworkPipeline.class) {
            if (instance == null) {
                instance = new NetworkPipeline();
            }

            return instance;
        }
    }

    @Override
    protected void initChannel(Channel channel) {
        /* create session */
        channel.attr(NetworkManager.session).set(
                new Player(channel)
        );

        ChannelPipeline pipeline = channel.pipeline();

        /* legacy-server-ping-handler */
        pipeline.addLast("legacy-server-ping-handler", new LegacyPingHandler());

        /* length-frame */
        pipeline.addLast("length-frame-decoder", new LengthFrameDecoder());
        pipeline.addLast("length-frame-encoder", new LengthFrameEncoder());

        /* message-packet */
        pipeline.addLast("message-packet-decoder", new MessagePacketDecoder());
        pipeline.addLast("message-packet-encoder", new MessagePacketEncoder());

        /* handler */
        pipeline.addLast("network-handler", new NetworkHandler());
    }
}
