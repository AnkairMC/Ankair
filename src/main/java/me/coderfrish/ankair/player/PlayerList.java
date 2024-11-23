package me.coderfrish.ankair.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    public final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        if (players.contains(player)) {
            throw new RuntimeException("Player is exist.");
        }

        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public int online() {
        return players.size();
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public List<Player> entry() {
        return players;
    }
}
