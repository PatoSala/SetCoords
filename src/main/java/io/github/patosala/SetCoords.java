package io.github.patosala;

import io.github.patosala.commands.*;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SetCoords extends JavaPlugin {

    public HashMap<String, HashMap<String, Location>> coordinates;
    public CoordsFile file;

    public void addCoordinates(String uuid, String locationName, Location coords) {
        // Check if user already has any key assigned
        HashMap<String, Location> userCoords = this.coordinates.get(uuid);
        if (userCoords == null) {
            // if empty, we create a new empty hashmap
            userCoords = new HashMap<>();
            // assigned given coords to the hashmap
            userCoords.put(locationName, coords);
            // save new coords to global coordinates instance variable
            this.coordinates.put(uuid, userCoords);
        } else {
            // if user already has a key inside coordinates
            userCoords.put(locationName, coords);
            this.coordinates.put(uuid, userCoords);
        }
    }

    public HashMap<String, Location> getCoordinates(String uuid) {
        HashMap<String, Location> userCoords = this.coordinates.get(uuid);
        return userCoords;
    }

    public Location removeCoordinates(String userId, String name) {
        HashMap<String, Location> userCoords = this.coordinates.get(userId);
        return userCoords.remove(name);
    }

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");

        this.coordinates = new HashMap<>();
        this.file = new CoordsFile();
        this.file.init(this);

        this.getCommand("setcoords").setExecutor(new SetCoordsCommand(this));
        this.getCommand("listcoords").setExecutor(new ListCoordsCommand(this));
        this.getCommand("removecoords").setExecutor(new RemoveCoordsCommand(this));
        this.getCommand("getcoords").setExecutor(new GetCoordsCommand());
        this.getCommand("pointcoords").setExecutor(new PointCoordsCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
        this.file.terminate(this);
    }



}

