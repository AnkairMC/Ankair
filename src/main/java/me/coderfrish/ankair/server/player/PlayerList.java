package me.coderfrish.ankair.server.player;

import me.coderfrish.ankair.network.ClientConnection;
import me.coderfrish.ankair.player.Player;
import me.coderfrish.ankair.server.network.game.ClientBoundGameKeepAlivePacket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerList {
    public final List<Player> players = new ArrayList<>();

    public void addPlayer(ClientConnection connection) {
        Player player = connection.getPlayer();
        if (players.contains(player)) {
            throw new RuntimeException("Player is exist.");
        }
        players.add(player);

        connection.setKeepAliveScheduledFuture(connection.getChannel().eventLoop().scheduleAtFixedRate(
                () -> connection.sendPacket(
                        new ClientBoundGameKeepAlivePacket((int) (System.currentTimeMillis() / 10000))
                ),
                5, 10, TimeUnit.SECONDS));
    }

    public void removePlayer(ClientConnection connection) {
        if (players.contains(connection.getPlayer())) {
            connection.getKeepAliveScheduledFuture().cancel(true);
            players.remove(connection.getPlayer());
        }
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
