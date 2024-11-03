package xyz.ankairmc.ankair;

import xyz.ankairmc.ankair.network.NetworkManager;
import xyz.ankairmc.ankair.player.Player;
import xyz.ankairmc.ankair.server.MinecraftCustom;
import xyz.ankairmc.ankair.server.MinecraftRunnable;
import xyz.ankairmc.ankair.server.MinecraftStatus;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public abstract class MinecraftServer implements MinecraftStatus, MinecraftCustom {
    private static final List<Player> players = new ArrayList<>();
    private static MinecraftServer server;

    protected NetworkManager netManager;

    protected MinecraftServer() {
        if (server == null) {
            server = this;
        }
    }

    public abstract boolean initServer();

    public void runServer() {
        this.netManager.runServer();
    }

    public void stopServer() {
        this.netManager.stopServer();
    }

    public static MinecraftServer getServer() {
        return server;
    }

    public static void newServer(
            MinecraftRunnable<MinecraftServer> runnable
    ) {
        new IntegratedServer(runnable);
    }

    public InetSocketAddress getServerAddress() {
        return new InetSocketAddress(25565);
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static void addPlayer(Player player) {
        if (!getPlayers().contains(player)) {
            getPlayers().add(player);
        } else {
            player.disconnect("The player already exists.");
        }
    }

    public static void removePlayer(Player player) {
        if (getPlayers().contains(player)) {
            getPlayers().remove(player);
        } else {
            player.disconnect("The player dose not exists.");
        }
    }

    public static void main(String[] args) {
        MinecraftServer.newServer((server) -> {
            if (server.initServer()) {
                server.runServer();
            }
        });
    }
}
