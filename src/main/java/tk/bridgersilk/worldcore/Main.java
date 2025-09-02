package tk.bridgersilk.worldcore;

import net.minestom.server.MinecraftServer;
import tk.bridgersilk.worldcore.server.InitServer;

public class Main {
    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();

        InitServer.init(server);

        server.start("0.0.0.0", 25565);
    }
}