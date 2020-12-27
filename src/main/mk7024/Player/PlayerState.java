package main.mk7024.Player;


import main.mk7024.Duel;
import org.bukkit.entity.Player;

public enum PlayerState {
    INLOBBY,INGAME;


    public static boolean canJoin(Player player){
        return Duel.getPlayerManager().getState(player).equals(INLOBBY);
    }
}
