package tk.bridgersilk.worldcore.commands;

import net.minestom.server.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.command.CommandActor;

public class GlobalCommands {
    @Command({"msg", "tell"})
    public void msgCommand(CommandActor actor, Player target, String msg) {
        var actor_msg = "You -> " + target.getUsername() + ": " + msg;
        var target_msg = target.getUsername() + " -> You: " + msg;
        actor.reply("§7" + actor_msg);
        target.sendMessage("§7" + target_msg);
    }
}
