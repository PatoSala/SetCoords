package io.github.patosala.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCoordsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = player.getLocation();

            for (Player onlinePLayers : Bukkit.getOnlinePlayers()) {
                onlinePLayers.sendMessage(ChatColor.GOLD + player.getName() + "'s corrdinates are " + ChatColor.GRAY + "XYZ: " + loc.getBlockX() + " / " + loc.getBlockY() + " / " + loc.getBlockZ() + " (" + loc.getWorld().getName() + ")");
            }
        }
        return true;
    }
}
