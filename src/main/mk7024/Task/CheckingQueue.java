package main.mk7024.Task;

import main.mk7024.Duel;
import main.mk7024.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CheckingQueue extends BukkitRunnable {
    @Override
    public void run(){
        CheckingQueue.checkQueue();
    }

    public static void checkQueue(){
//        int count = 0;
        if(Duel.getLobby().getQueue().size() >= 2){
            Duel.getGameManager().startingAGame();
//            for(Player player : Bukkit.getOnlinePlayers()){
//                if(Lobby.getQueue().get(0).equals(player.getUniqueId()) || Lobby.getQueue().get(1).equals(player.getUniqueId())){
//                    Duel.getGameManager().startingAGame();
//                }
//                for(UUID uuid : Lobby.getQueue()){
//                    Duel.getGameManager().startingAGame();
//                }
            }
        }
    }
