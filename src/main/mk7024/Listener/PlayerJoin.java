package main.mk7024.Listener;

import main.mk7024.Duel;
import main.mk7024.Kit;
import main.mk7024.Lobby;
import main.mk7024.Player.PlayerState;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(!player.hasPlayedBefore()){
            if(!Duel.getSql().isInDataBase(player)){
                Duel.getSql().addData(player.getUniqueId());
            }
        }
        Kit.setLobbyItem(player);

        e.setJoinMessage(null);
        Duel.getPlayerManager().addPlayer(player);
        Duel.getLobby().teleportToLobby(player);
    }

}
