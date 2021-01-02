package main.mk7024.Listener;

import main.mk7024.Duel;
import main.mk7024.Player.PlayerManager;
import main.mk7024.Player.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(Duel.getPlayerManager().getState(player).equals(PlayerState.STARTING)){
            event.setCancelled(true);
        }
    }
}
