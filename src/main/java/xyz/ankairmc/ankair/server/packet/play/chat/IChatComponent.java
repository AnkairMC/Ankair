package xyz.ankairmc.ankair.server.packet.play.chat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public interface IChatComponent {
    IChatComponent text(String text);

    IChatComponent color(String color);

    IChatComponent translate(String translate);

    IChatComponent extra(IChatComponent translate);

    JsonObject toJson();

    String toString();

    static ChatComponent build() {
        return new ChatComponent();
    }

    class ChatComponent implements IChatComponent {
        JsonArray extra = new JsonArray();
        JsonObject jsonObject = new JsonObject();

        public IChatComponent text(String text) {
            if (!jsonObject.has("text")) {
                jsonObject.addProperty("text", text);
            }

            return this;
        }

        public IChatComponent color(String color) {
            if (!jsonObject.has("color")) {
                jsonObject.addProperty("color", color);
            }

            return this;
        }

        @Override
        public IChatComponent translate(String translate) {
            if (!jsonObject.has("translate")) {
                jsonObject.addProperty("translate", translate);
            }

            return this;
        }

        @Override
        public IChatComponent extra(IChatComponent extra) {
            if (!this.extra.contains(extra.toJson())) {
                this.extra.add(extra.toJson());
            }

            return this;
        }

        @Override
        public JsonObject toJson() {
            return this.jsonObject;
        }

        @Override
        public String toString() {
            if (!extra.isEmpty()) this.jsonObject.add("extra", this.extra);

            return new Gson().toJson(this.jsonObject);
        }
    }
}
