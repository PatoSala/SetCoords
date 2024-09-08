package io.github.patosala;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class CoordsFile {

    public void init(SetCoords plugin) {
        System.out.println("INIT COORDS FILE");
        File coordsFile = new File(plugin.getDataFolder(), "coords.yml");
        if (coordsFile.exists()) {
            YamlConfiguration coordsConfig = YamlConfiguration.loadConfiguration(coordsFile);
            for (String userId : coordsConfig.getKeys(false)) {
                String[] savedUserLocations = coordsConfig.getConfigurationSection(userId).getKeys(false).toArray(new String[0]);
                for (String locationName : savedUserLocations) {
                    Location loc = coordsConfig.getLocation(userId + "." + locationName);
                    plugin.addCoordinates(userId, locationName, loc);
                }
            }
        }
    }

    public void terminate(SetCoords plugin) {
        File coordsFile = new File(plugin.getDataFolder(), "coords.yml");

        if (!coordsFile.exists()) {
            System.out.println("No coords file found");
            try {
                coordsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration coordsConfig = YamlConfiguration.loadConfiguration(coordsFile);
        for (String userId : plugin.coordinates.keySet()) {
            for (String locationName : plugin.coordinates.get(userId).keySet()) {
                coordsConfig.createSection(userId + "." + locationName);
                coordsConfig.set(userId + "." + locationName, plugin.coordinates.get(userId).get(locationName));
            }
        }

        try {
            coordsConfig.save(coordsFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(SetCoords plugin, String userId, String locationName) {
        File coordsFile = new File(plugin.getDataFolder(), "coords.yml");

        if (coordsFile.exists()) {
            YamlConfiguration coordsConfig = YamlConfiguration.loadConfiguration(coordsFile);
            coordsConfig.set(userId + "." + locationName, null);

            try {
                coordsConfig.save(coordsFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
