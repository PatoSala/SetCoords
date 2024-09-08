package io.github.patosala.commands;

import io.github.patosala.SetCoords;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RemoveCoordsCommand implements CommandExecutor {
    public SetCoords plugin;

    public RemoveCoordsCommand(SetCoords plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String locationName = args[0].toLowerCase();
            HashMap<String, Location> savedPlayerCoordinates = this.plugin.getCoordinates(player.getUniqueId().toString());

            if (savedPlayerCoordinates.containsKey(locationName)) {
                savedPlayerCoordinates.remove(locationName);
                this.plugin.file.delete(this.plugin, player.getUniqueId().toString(), locationName);
                player.sendMessage(ChatColor.AQUA + "Removed coords: " + locationName);
            } else {
                player.sendMessage(ChatColor.RED + "Location not found.");
            }
        }
        return true;
    }
}
