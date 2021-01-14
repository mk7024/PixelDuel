package main.mk7024.Player;

import it.unimi.dsi.fastutil.Hash;
import main.mk7024.Duel;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {
    private HashSet<GamePlayer> allplayer = new HashSet<>();

    public HashSet<GamePlayer> getOnlineplayer() {
        return allplayer;
    }
    public void addPlayer(Player player){
        allplayer.add(new GamePlayer(player));
    }

    public void setStarting(Player player){
        getGamePlayer(player).setState(PlayerState.STARTING);
    }
    public void setInGame(Player player){
        getGamePlayer(player).setState(PlayerState.INGAME);
    }
    public void removePlayer(Player player){
        allplayer.remove(getGamePlayer(player));
    }

    public GamePlayer getGamePlayer(Player player){
        for(GamePlayer gameplayer : allplayer){
            if(gameplayer.getName().equalsIgnoreCase(player.getName())){
                return gameplayer;
            }
        }
        return null;
    }

    public void setInLobby(Player player){
        getGamePlayer(player).setState(PlayerState.INLOBBY);
    }
    public PlayerState getState(Player player){
        return getGamePlayer(player).getState();
    }
}
