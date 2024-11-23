package me.coderfrish.ankair.core.chat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ChatMessageBuilder {
    private final JsonArray extra = new JsonArray();
    private final JsonObject message;

    public ChatMessageBuilder() {
        this.message = new JsonObject();
    }

    public JsonObject getMessage() {
        return message;
    }

    public ChatMessageBuilder text(String text) {
        message.addProperty("text", text);
        return this;
    }

    public ChatMessageBuilder color(String color) {
        message.addProperty("color", color);
        return this;
    }

    public ChatMessageBuilder extra(ChatMessageBuilder extra) {
        this.extra.add(extra.getMessage());
        return this;
    }

    public String build() {
        if (!extra.isEmpty()) {
            message.add("extra", extra);
        }

        return new Gson().toJson(message);
    }
}
