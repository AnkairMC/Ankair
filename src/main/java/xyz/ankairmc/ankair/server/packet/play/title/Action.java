package xyz.ankairmc.ankair.server.packet.play.title;

public enum Action {
    SET_TITLE(0),
    SET_SUBTITLE(1),
    SET_ACTION_BAR(2),
    SET_TIMES_AND_DISPLAY(3),
    HIDE(4),
    RESET(5);

    public final int id;

    Action(int id) {
        this.id = id;
    }
}
