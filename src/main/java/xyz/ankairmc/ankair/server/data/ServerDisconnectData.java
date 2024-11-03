package xyz.ankairmc.ankair.server.data;

import com.google.gson.Gson;

public record ServerDisconnectData(String text) {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
