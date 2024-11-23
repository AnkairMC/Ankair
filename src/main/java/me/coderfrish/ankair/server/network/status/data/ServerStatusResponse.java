package me.coderfrish.ankair.server.network.status.data;

import com.google.gson.Gson;

public record ServerStatusResponse(Version version, Players players, Description description) {
    public record Version(int protocol, String name) {}

    public record Players(int max, int online) {}

    public record Description(String text) {}

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
