package me.coderfrish.ankair.server.network.handshaking;

import me.coderfrish.ankair.network.ClientConnection;
import me.coderfrish.ankair.server.network.login.TCPLoginListener;
import me.coderfrish.ankair.server.network.status.TCPStatusListener;

public class TCPHandshakingListener implements ITCPHandshakingListener {
    private final ClientConnection connection;

    public TCPHandshakingListener(ClientConnection connection) {
        this.connection = connection;
    }

    @Override
    public void handleHandshaking(ServerBoundHandshakingPacket packet) {
        connection.setState(packet.nextState);
        connection.getProfile().setProtocol(packet.protocol);

        switch (connection.getState()) {
            case STATE -> connection.setListener(new TCPStatusListener(connection));
            case LOGIN -> connection.setListener(new TCPLoginListener(connection));

            default -> throw new RuntimeException("Unknown state: " + connection.getState());
        }
    }

    @Override
    public void disconnect(String reason) {
        throw new UnsupportedOperationException("Not implementation yet.");
    }
}
