package me.coderfrish.ankair;

import me.coderfrish.ankair.network.ServerConnection;

public class IntegratedServer extends MinecraftServer {
    private final Runtime runtime = Runtime.getRuntime();

    @Override
    public void initServer() {
        runtime.addShutdownHook(new Thread(this::stopServer));
        this.serverConnection = new ServerConnection();

        initialized = !initialized;
        this.runServer();
    }
}
