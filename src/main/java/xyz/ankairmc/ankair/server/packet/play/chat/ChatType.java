package xyz.ankairmc.ankair.server.packet.play.chat;

public enum ChatType {
    CHAT(0),
    SYS_MSG(1),
    GAME_INFO(2);

    public final int id;

    ChatType(int id) {
        this.id = id;
    }
}
