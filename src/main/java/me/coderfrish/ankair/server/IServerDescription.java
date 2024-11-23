package me.coderfrish.ankair.server;

public interface IServerDescription {
    int getPlayersOnline();

    int getPlayersMax();

    int getServerProtocol();

    String getServerDescription();

    String getServerName();

    String getServerModName();
}
