package io.github.patosala.commands;
import io.github.patosala.SetCoords;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ListCoordsCommand implements CommandExecutor {
    public SetCoords plugin;

    public ListCoordsCommand(SetCoords plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            HashMap<String, Location> savedPlayerCoordinates = this.plugin.getCoordinates(player.getUniqueId().toString());

            if (savedPlayerCoordinates != null && savedPlayerCoordinates.size() > 0) {
                player.sendMessage(ChatColor.AQUA + "List of saved coordinates of " + ChatColor.DARK_PURPLE +  player.getDisplayName() + ":");
                for (String locationName : savedPlayerCoordinates.keySet()) {
                    Location location = savedPlayerCoordinates.get(locationName);
                    player.sendMessage(ChatColor.GOLD + " - " + locationName + " " + ChatColor.WHITE + location.getWorld().getName() + ChatColor.GRAY + " XYZ: " + location.getBlockX() + " / " + location.getBlockY() + " / " + location.getBlockZ());
                };
            } else {
                player.sendMessage(ChatColor.AQUA + "No coordinates found. Start adding by using /setcoords <name>.");
            }
        }
        return true;
    }
}
