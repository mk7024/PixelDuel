package main.mk7024.Game;

import main.mk7024.Duel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class Game {
    private GameState gameState;
    private Duel duel;
    private HashSet<UUID> player = new HashSet<>();
    private World w;
    private String name;
    private Location location1;
    private Location location2;


    public Game(String name){
        this.name = name;
        w = Bukkit.getWorld(name);
        gameState = GameState.INLOBBY;
        duel = Duel.getPlugin();
        location1 = new Location(w,duel.getConfig().getDouble("Game." + name + ".spawnlocation.x1"),duel.getConfig().getDouble("Game." + name + ".spawnlocation.y1"),duel.getConfig().getDouble("Game." + name + ".spawnlocation.z1"));
        location1 = new Location(w,duel.getConfig().getDouble("Game." + name + ".spawnlocation.x2"),duel.getConfig().getDouble("Game." + name + ".spawnlocation.y2"),duel.getConfig().getDouble("Game." + name + ".spawnlocation.z2"));
    }

    public GameState getGameState(){
        return gameState;
    }

    public void addPlayer(Player p){
        player.add(p.getUniqueId());
    }

    public void removeAllPlayer(){
        player.clear();
    }

    public Location getLocation1(){
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }
}
