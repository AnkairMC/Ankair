package me.coderfrish.ankair.util;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@SuppressWarnings("all")
public class ProfileUtil {
    public static UUID getUUIDByPlayerUsername(String username) {
        return UUID.nameUUIDFromBytes(
                ("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8)
        );
    }
}
