package main.mk7024.Game;

import main.mk7024.Duel;
import main.mk7024.Kit;
import main.mk7024.Lobby;
import main.mk7024.Task.StartingTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class GameManager {
    private List<Game> allgame = new ArrayList<>();

    public List<Game> getAllgame() {
        return allgame;
    }

    public void startingAGame(){
        Game availablegame = getAvailableGame();
        if(availablegame != null){
            ArrayList<Player> tobeteleported = new ArrayList<>();
            for(Player player : Bukkit.getOnlinePlayers()){
                if(Duel.getLobby().isInQueue(player)){
                    tobeteleported.add(player);
                    Duel.getPlayerManager().setStarting(player);
                    availablegame.addPlayer(player);
                    Duel.getLobby().removeFromQueue(player);
                    Kit.setGameItem(player);
                }
            }
            tobeteleported.get(0).teleport(availablegame.getLocation1());
            tobeteleported.get(1).teleport(availablegame.getLocation2());
            availablegame.setInGame();
            BukkitRunnable startingtitle = new StartingTitle(tobeteleported,availablegame);
            BukkitTask taskid = startingtitle.runTaskTimer(Duel.getPlugin(),0,20);
            availablegame.setStartingtask(taskid);
        }else{
            for(Player player : Bukkit.getOnlinePlayers()){
                if(Duel.getLobby().isInQueue(player)){
                    player.sendMessage(ChatColor.RED + "目前暂无空闲游戏地图,请稍等!");
                }
            }
        }
    }

    public void startAGame(ArrayList<Player> player){
        for(Player p : player){
            Duel.getPlayerManager().setInGame(p);
        }
    }

    public Game getPlayerInWhichGame(Player player){
        for(Game game : allgame){
            if(game.getPlayer().contains(player.getUniqueId())){
                return game;
            }
        }
        return null;
    }

    public Game getAvailableGame(){
        for(Game game : allgame){
            if(GameState.canJoin(game)){
                return game;
            }
        }
        return null;
    }
}
