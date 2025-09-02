package tk.bridgersilk.worldcore.listener;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.coordinate.Pos;

public class PlayerBlockBreakListener {
    public static void register() {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            var material = event.getBlock().registry().material();
            if (material != null && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                var itemStack = ItemStack.of(material);
                ItemEntity itemEntity = new ItemEntity(itemStack);
                itemEntity.setInstance(event.getInstance(), event.getBlockPosition());
                itemEntity.setPickupDelay(Duration.ofMillis(500));
                ThreadLocalRandom rnd = ThreadLocalRandom.current();
                itemEntity.refreshPosition(new Pos(event.getBlockPosition().x() + 0.5, event.getBlockPosition().y() + 0.5, event.getBlockPosition().z() + 0.5));
                itemEntity.setVelocity(new Vec(rnd.nextDouble(-1, 1), 1, rnd.nextDouble(-1, 1)));
            }
        });
    }
}
