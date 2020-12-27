package main.mk7024.Task;

import main.mk7024.Duel;
import main.mk7024.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckingQueue extends BukkitRunnable {
    @Override
    public void run(){
        CheckingQueue.checkQueue();
    }

    public static void checkQueue(){
        if(Lobby.getQueue().size() == 2){
            for(Player player : Bukkit.getOnlinePlayers()){
                if(Lobby.getQueue().contains(player.getUniqueId())){
                    Duel.getGameManager().startAGame(Lobby.getQueue());
                }
            }
        }
    }
}
