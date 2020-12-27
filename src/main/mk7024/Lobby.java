package main.mk7024;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Lobby {
    private static HashSet<UUID> queue = new HashSet<>();

    public static HashSet<UUID> getQueue() {
        return queue;
    }
    public static void addToQueue(Player p) {
        queue.add(p.getUniqueId());
    }
    public static void removeFromQueue(Player p){
        queue.remove(p.getUniqueId());
    }
    public static void clearQueue(){
        queue.clear();
    }
}
