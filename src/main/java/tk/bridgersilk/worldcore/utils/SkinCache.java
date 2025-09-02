package tk.bridgersilk.worldcore.utils;

import net.minestom.server.entity.PlayerSkin;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SkinCache {
    private static final Map<String, PlayerSkin> CACHE = new ConcurrentHashMap<>();

    public static PlayerSkin getByUUID(String uuid) {
        return CACHE.computeIfAbsent(uuid, key -> PlayerSkin.fromUuid(key));
    }

    public static PlayerSkin getByUsername(String username) {
        return CACHE.computeIfAbsent(username.toLowerCase(), key -> PlayerSkin.fromUsername(key));
    }
}