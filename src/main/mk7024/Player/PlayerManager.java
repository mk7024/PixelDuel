package main.mk7024.Player;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {
    private HashMap<UUID,PlayerState> allplayer = new HashMap<>();

    public HashMap<UUID, PlayerState> getOnlineplayer() {
        return allplayer;
    }
    public void addPlayer(Player player){
        allplayer.put(player.getUniqueId(),PlayerState.INLOBBY);
    }
    public void setStarting(Player player){
        allplayer.replace(player.getUniqueId(),PlayerState.STARTING);
    }
    public void setInGame(Player player){
        allplayer.replace(player.getUniqueId(),PlayerState.INGAME);
    }
    public void removePlayer(Player player){
        allplayer.remove(player.getUniqueId());
    }
    public void setInLobby(Player player){
        allplayer.replace(player.getUniqueId(),PlayerState.INLOBBY);
    }
    public PlayerState getState(Player player){
        return allplayer.get(player.getUniqueId());
    }
}
