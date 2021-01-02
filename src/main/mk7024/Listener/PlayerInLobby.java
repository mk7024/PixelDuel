package main.mk7024.Listener;

import main.mk7024.Duel;
import main.mk7024.Kit;
import main.mk7024.Lobby;
import main.mk7024.Player.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerInLobby implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            if(PlayerState.notInGame((Player) event.getWhoClicked())){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            if(PlayerState.notInGame((Player) event.getEntity())){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(PlayerState.notInGame(event.getPlayer())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Duel.getLobby().teleportToLobby(event.getPlayer());
        Kit.setLobbyItem(event.getPlayer());
    }

}
