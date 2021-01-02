package main.mk7024.Player;


import main.mk7024.Duel;
import org.bukkit.entity.Player;

public enum PlayerState {
    INLOBBY,INGAME,STARTING;


    public static boolean canJoin(Player player){
        return Duel.getPlayerManager().getState(player).equals(INLOBBY);
    }
    public static boolean notInGame(Player player){
        return Duel.getPlayerManager().getState(player).equals(INLOBBY)||Duel.getPlayerManager().getState(player).equals(STARTING);
    }
}
