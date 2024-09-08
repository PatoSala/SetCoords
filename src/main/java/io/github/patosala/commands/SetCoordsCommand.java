package io.github.patosala.commands;

import io.github.patosala.SetCoords;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

public class SetCoordsCommand implements CommandExecutor {

    public SetCoords plugin;

    public SetCoordsCommand(SetCoords plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (sender instanceof Player) {
            if (args.length == 0) {
                player.sendMessage("Missing arguments");
                return false;
            }

            Location loc = player.getLocation();
            String locationName = args[0].toLowerCase();

            plugin.addCoordinates(player.getUniqueId().toString(), locationName, loc);
            // Save coords in CoordsFile for persistency
            player.sendMessage(ChatColor.GREEN + "Saved " + ChatColor.GOLD + locationName + ChatColor.GREEN + " at " + ChatColor.WHITE + "XYZ: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
        }
        return true;

    }
}
