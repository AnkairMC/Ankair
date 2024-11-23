package me.coderfrish.ankair.core.chat;

public interface IChatComponent {
    static ChatMessageBuilder text(String text) {
        return new ChatMessageBuilder().text(text);
    }

    static ChatMessageBuilder color(String text) {
        return new ChatMessageBuilder().color(text);
    }
}
