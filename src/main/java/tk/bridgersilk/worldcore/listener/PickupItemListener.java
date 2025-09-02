package tk.bridgersilk.worldcore.listener;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.PickupItemEvent;

public class PickupItemListener {
    public static void register() {    
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PickupItemEvent.class, event -> {
            if (event.getLivingEntity().canPickupItem() && event.getEntity() instanceof Player) {
                ((Player) event.getEntity()).getInventory().addItemStack(event.getItemStack());
            }
        });
    }
}
