package xyz.ankairmc.ankair.server;

public interface MinecraftStatus {
    int getProtocol();

    String getVersionName();

    String getMotd();

    int getMax();

    int getOnline();
}
