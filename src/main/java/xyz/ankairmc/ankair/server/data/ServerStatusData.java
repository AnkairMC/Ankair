package xyz.ankairmc.ankair.server.data;

import com.google.gson.Gson;

public record ServerStatusData(Version version, Players players, Description description) {
    public record Version(String name, int protocol) {}

    public record Players(int max, int online) {}

    public record Description(String text) {}

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
