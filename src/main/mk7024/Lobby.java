package main.mk7024;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class Lobby {
    private static ArrayList<UUID> queue = new ArrayList<>();
    private static HashMap<UUID,BukkitTask> taskid = new HashMap<>();
    private static Duel duel;
    private static Location lobbylocation;
    public Lobby(){
        duel = Duel.getPlugin();
        lobbylocation = new Location(Bukkit.getWorld("world"), duel.getConfig().getDouble("Lobby.location.x"), duel.getConfig().getDouble("Lobby.location.y"), duel.getConfig().getDouble("Lobby.location.z"),duel.getConfig().getInt("Lobby.location.yaw"),duel.getConfig().getInt("Lobby.location.pitch"));
    }

    public void teleportToLobby(Player player){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(duel, new Runnable() {
            public void run(){
                Chunk chunk = lobbylocation.getChunk();
                if(!chunk.isLoaded()){
                    chunk.load();
                }
                player.teleport(lobbylocation);
            }
        }, 2);
    }

    public HashMap<UUID, BukkitTask> getTaskid() {
        return taskid;
    }

    public List<UUID> getQueue() {
        return queue;
    }
    public void addToQueue(Player p) {
        queue.add(p.getUniqueId());
    }
    public void removeFromQueue(Player p){
        queue.remove(p.getUniqueId());
    }
    public boolean isInQueue(Player player){
        return queue.contains(player.getUniqueId());
    }
    public void clearQueue(){
        queue.clear();
    }
}
