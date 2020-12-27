package main.mk7024.Listener;

import main.mk7024.Duel;
import main.mk7024.Game.Game;
import main.mk7024.Lobby;
import main.mk7024.Player.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
        Player player = e.getPlayer();
        if(Lobby.getQueue().contains(player.getUniqueId())){
            Lobby.removeFromQueue(player);
        }
        Duel.getPlayerManager().removePlayer(player);

        //TODO:对战中退出
        if(!PlayerState.canJoin(Duel.getPlayerManager().getState(player))){
            Game game = Duel.getGameManager().getPlayerInWhichGame(player);
            game.removePlayer(player);
        }
    }
}
