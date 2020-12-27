package main.mk7024.Player;

import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {
    private HashMap<UUID,PlayerState> allplayer = new HashMap<>();

    public Map<UUID, PlayerState> getOnlineplayer() {
        return allplayer;
    }

    public void addPlayer(Player player){
        allplayer.put(player.getUniqueId(),PlayerState.INLOBBY);
    }
    public void setInGame(Player player){
        allplayer.replace(player.getUniqueId(),PlayerState.INGAME);
    }
    public void removePlayer(Player player){
        allplayer.remove(player.getUniqueId());
    }
}
