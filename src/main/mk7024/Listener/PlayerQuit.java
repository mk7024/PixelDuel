package main.mk7024.Listener;

import com.google.common.hash.HashCode;
import main.mk7024.Duel;
import main.mk7024.Game.Game;
import main.mk7024.Lobby;
import main.mk7024.Player.PlayerState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
        Player player = e.getPlayer();
        Duel.getPlayerManager().removePlayer(player);

        //等待开始,对战时退出
        if(!PlayerState.canJoin(player)){
            Game game = Duel.getGameManager().getPlayerInWhichGame(player);
            game.removePlayer(player);
            for(Player p : Bukkit.getOnlinePlayers()){
                for(UUID uuid : game.getPlayer()){
                    if(p.getUniqueId().equals(uuid)){
                        game.cancelStartingtask();
                        Duel.getPlayerManager().setInLobby(p);
                        Duel.getLobby().teleportToLobby(p);
                        p.sendTitle(ChatColor.RED + "对方退出游戏!",null,10,30,10);
                        game.setInLobby();
                    }
                }
            }
            return;
        }
        //队列中退出
        if(Duel.getLobby().getQueue().contains(player.getUniqueId())){
            BukkitTask queuetask = Duel.getLobby().getTaskid().get(player.getUniqueId());
            Duel.getLobby().removeFromQueue(player);
            return;
        }

    }
}
