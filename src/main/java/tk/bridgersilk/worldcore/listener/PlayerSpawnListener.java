package tk.bridgersilk.worldcore.listener;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import tk.bridgersilk.worldcore.config.Config;
import tk.bridgersilk.worldcore.utils.SkinCache;

public class PlayerSpawnListener {

    public static void register(InstanceContainer instanceContainer) {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);

            double x = Config.getDouble("player.respawn_point.x");
            double y = Config.getDouble("player.respawn_point.y");
            double z = Config.getDouble("player.respawn_point.z");

            player.setRespawnPoint(new Pos(x, y, z));
            PlayerSkin skin = SkinCache.getByUsername(event.getPlayer().getUsername());
            player.setSkin(skin); // fix: why does this not work?
            player.setGameMode(GameMode.SURVIVAL);
        });
    }
}