package xyz.ankairmc.ankair;

import xyz.ankairmc.ankair.network.NetworkManager;
import xyz.ankairmc.ankair.server.MinecraftRunnable;

public class IntegratedServer extends MinecraftServer {
    public IntegratedServer(
            MinecraftRunnable<MinecraftServer> serverRunnable
    ) {
        serverRunnable.apply(this);
    }

    @Override
    public boolean initServer() {
        this.netManager = new NetworkManager(
                getServerAddress()
        );

        return true;
    }

    @Override
    public int getProtocol() {
        return 340;
    }

    @Override
    public String getVersionName() {
        return "Ankair 1.12.2";
    }

    @Override
    public String getMotd() {
        return "A Minecraft Server.";
    }

    @Override
    public int getMax() {
        return 10;
    }

    @Override
    public int getOnline() {
        return MinecraftServer.getPlayers().size();
    }

    @Override
    public String getServerModName() {
        return "Ankair";
    }
}
