package main.mk7024.Game;

import main.mk7024.Duel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GameManager {
    private List<Game> allgame = new ArrayList<>();

    public List<Game> getAllgame() {
        return allgame;
    }

    public void startAGame(HashSet hashSet){
        Game availablegame = getAvailableGame();
        if(availablegame != null){
            ArrayList<Player> tobeteleported = null;
            for(Player player : Bukkit.getOnlinePlayers()){
                tobeteleported.add(player);
                Duel.getPlayerManager().setInGame(player);
            }
            tobeteleported.get(0).teleport(availablegame.getLocation1());
            tobeteleported.get(1).teleport(availablegame.getLocation2());
        }
    }

    public Game getPlayerInWhichGame(Player player){
        for(Game game : allgame){
            if(game.getPlayer().contains(player)){
                return game;
            }
        }
        return null;
    }

    public Game getAvailableGame(){
        int i = 0;
        for(Game game : allgame){
            if(GameState.canJoin(game.getGameState())){
                return game;
            }
        }
        return null;
    }
}
