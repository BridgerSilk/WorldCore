package tk.bridgersilk.worldcore.server;

import tk.bridgersilk.worldcore.listener.PlayerSpawnListener;
import tk.bridgersilk.worldcore.config.Config;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.LightingChunk;
import tk.bridgersilk.worldcore.listener.PickupItemListener;
import tk.bridgersilk.worldcore.listener.PlayerBlockBreakListener;

public class InitServer {

    public static void init(MinecraftServer server) {
        // get config
        Config.load();

        // brand
        MinecraftServer.setBrandName("WorldCore");

        // create world instance
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setGenerator(unit -> unit.modifier().fillHeight(0, 5, Block.GRASS_BLOCK));
        instanceContainer.setChunkSupplier(LightingChunk::new);

        // register listeners
        PlayerSpawnListener.register(instanceContainer);
        PlayerBlockBreakListener.register();
        PickupItemListener.register();
    }
}