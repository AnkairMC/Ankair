package me.coderfrish.ankair;

import io.netty.util.concurrent.ScheduledFuture;
import me.coderfrish.ankair.network.ServerConnection;
import me.coderfrish.ankair.server.player.PlayerList;
import me.coderfrish.ankair.server.IServerDescription;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public abstract class MinecraftServer implements IServerDescription, Runnable {
    public static final String MINECRAFT_VERSION = "1.15.2";
    private final PlayerList playerList;
    protected boolean initialized = false;
    protected boolean started = false;
    protected ScheduledFuture<?> tickScheduledFuture;
    protected ServerConnection serverConnection;
    private final InetSocketAddress address;
    private static MinecraftServer instance;

    public MinecraftServer() {
        this.address = new InetSocketAddress(25565);
        this.playerList = new PlayerList();

        instance = this;
    }

    public static MinecraftServer getServer() {
        return instance;
    }

    public abstract void initServer();

    public void runServer() {
        if (!initialized) return;
        serverConnection.runManager();
        tickScheduledFuture = serverConnection.getFuture().channel().eventLoop().scheduleAtFixedRate(
                this,
                0,
                50,
                TimeUnit.MILLISECONDS
        );

        started = !started;
    }

    public void stopServer() {
        if (!started) return;

        tickScheduledFuture.cancel(true);
        serverConnection.stopManager();
    }

    @Override
    public int getPlayersMax() {
        return 20;
    }

    @Override
    public int getPlayersOnline() {
        return playerList.online();
    }

    @Override
    public int getServerProtocol() {
        return 578;
    }

    @Override
    public String getServerModName() {
        return "Ankair";
    }

    @Override
    public String getServerDescription() {
        return "A Minecraft Server.";
    }

    @Override
    public String getServerName() {
        return String.format("Ankair %s", MINECRAFT_VERSION);
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    @Override
    public void run() {
        /* tick... */
    }

    public static void main(String[] args) {
        IntegratedServer server = new IntegratedServer();
        server.initServer();
    }
}
