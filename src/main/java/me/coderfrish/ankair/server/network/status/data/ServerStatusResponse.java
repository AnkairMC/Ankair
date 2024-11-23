package me.coderfrish.ankair.server.network.status.data;

import com.google.gson.*;
import me.coderfrish.ankair.player.Player;
import me.coderfrish.ankair.player.PlayerList;

@SuppressWarnings("all")
public class ServerStatusResponse {
    private final PlayerList playerList;
    private final JsonObject response = new JsonObject();

    public ServerStatusResponse(Version version, Players players, Description description, PlayerList playerList) {
        this.playerList = playerList;
        this.addVersion(version);
        this.addPlayers(players);
        this.addDescription(description);
    }

    public void addVersion(Version version) {
        response.add("version", JsonParser.parseString(
                new Gson().toJson(version)
        ).getAsJsonObject());
    }

    public void addPlayers(Players players) {
        response.add("players", JsonParser.parseString(
                new Gson().toJson(players)
        ).getAsJsonObject());
    }

    public void addDescription(Description description) {
        response.add("description", JsonParser.parseString(
                new Gson().toJson(description)
        ).getAsJsonObject());
    }

    public static class Version {
        private final int protocol;
        private final String name;

        public Version(int protocol, String name) {
            this.protocol = protocol;
            this.name = name;
        }
    }

    public static class Players {
        private final int max, online;

        public Players(int max, int online) {
            this.max = max;
            this.online = online;
        }
    }

    public static class Description {
        private final String text;

        public Description(String text) {
            this.text = text;
        }
    }

    @Override
    public String toString() {
        JsonObject response = this.response;
        if (!playerList.isEmpty()) {
            JsonObject players = response.getAsJsonObject("players");
            JsonArray array = new JsonArray();

            for (Player player : playerList.entry()) {
                JsonObject playerJson = new JsonObject();

                playerJson.addProperty("name", player.getProfile().getUsername());
                playerJson.addProperty("id", player.getProfile().getUuid().toString());
                array.add(playerJson);
            }

            players.add("sample", array);
        }

        return new Gson().toJson(response);
    }
}
