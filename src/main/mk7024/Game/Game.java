package main.mk7024.Game;

import main.mk7024.Duel;
import main.mk7024.Kit;
import main.mk7024.Lobby;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Game {
    private GameState gameState;
    private Duel duel;
    private List<UUID> player = new ArrayList<>();
    private World w;
    private String name;
    private Location location1;
    private Location location2;
    private BukkitTask startingtask;


    public Game(String name){
        this.name = name;
        new WorldCreator(name).createWorld();
        w = Bukkit.getWorld(name);
        w.setGameRuleValue("announceAdvancement","false");
        this.gameState = GameState.INLOBBY;
        duel = Duel.getPlugin();
        location1 = new Location(w,duel.getConfig().getDouble("Game." + name + ".spawnlocation.x1"),duel.getConfig().getDouble("Game." + name + ".spawnlocation.y1"),duel.getConfig().getDouble("Game." + name + ".spawnlocation.z1"),duel.getConfig().getInt("Game." + name + ".spawnlocation.yaw1"),duel.getConfig().getInt("Game." + name + ".spawnlocation.pitch1"));
        location2 = new Location(w,duel.getConfig().getDouble("Game." + name + ".spawnlocation.x2"),duel.getConfig().getDouble("Game." + name + ".spawnlocation.y2"),duel.getConfig().getDouble("Game." + name + ".spawnlocation.z2"),duel.getConfig().getInt("Game." + name + ".spawnlocation.yaw2"),duel.getConfig().getInt("Game." + name + ".spawnlocation.pitch2"));
    }

    public List<UUID> getPlayer() {
        return player;
    }

    public void setStartingtask(BukkitTask startingtask) {
        this.startingtask = startingtask;
    }

    public void cancelStartingtask(){
        startingtask.cancel();
    }

    public GameState getGameState(){
        return gameState;
    }

    public void setInGame(){
        gameState = GameState.INGAME;
    }

    public void setInLobby(){
        gameState = GameState.INLOBBY;
    }

    public void addPlayer(Player p){
        player.add(p.getUniqueId());
    }

    public void removePlayer(Player p){
        player.remove(p.getUniqueId());
    }

    public Location getLocation1(){
        setInGame();
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }

    public String getName() {
        return name;
    }

    public void setWinner(Player p){
        Duel.getLobby().teleportToLobby(p);
        Duel.getPlayerManager().setInLobby(p);
        Kit.setLobbyItem(p);
        Duel.getPlayerManager().removeEffect(p);
        Duel.getSql().setData(p.getUniqueId(),"kills");
        Duel.getSql().setData(p.getUniqueId(),"gameplay");
        p.sendTitle(ChatColor.RED + "恭喜你,获胜!",null,10,30,10);
        player.clear();
        p.setHealth(20.0);
        gameState = GameState.INLOBBY;
    }
}
