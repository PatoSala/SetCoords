package io.github.patosala.commands;

import io.github.patosala.SetCoords;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PointCoordsCommand  implements CommandExecutor {
    SetCoords plugin;

    public PointCoordsCommand(SetCoords plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            HashMap<String, Location> savedPlayerCoordinates = this.plugin.getCoordinates(player.getUniqueId().toString());

            if (savedPlayerCoordinates.containsKey(args[0])) {
                Location coords = savedPlayerCoordinates.remove(args[0]);
                player.setCompassTarget(coords);
                player.sendMessage(ChatColor.GOLD + "Pointing to " + ChatColor.WHITE + "XYZ: " + coords.getBlockX() + " / " + coords.getBlockY() + " / " + coords.getBlockZ());
            }
        }
        return true;
    }
}
