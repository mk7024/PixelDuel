package main.mk7024.Task;

import main.mk7024.Duel;
import main.mk7024.Game.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class StartingTitle extends BukkitRunnable {
    ArrayList<Player> sp = new ArrayList<>();
    Game game;
    int i = 10;
    public StartingTitle(ArrayList<Player> sp, Game game){
        this.sp = sp;
        this.game = game;
    }

    @Override
    public void run(){
        for(Player p : sp){
            p.sendTitle(getColor(i),null,5,10,5);
        }
        i--;
        if(i==-1){
            Duel.getGameManager().startAGame(sp);
            cancel();
        }
    }

    private String getColor(int i){
        if(i>3)
            return ChatColor.GREEN +String.valueOf(i);
        if(i>=1 && i<=3)
            return ChatColor.YELLOW +String.valueOf(i);
        if(i==0)
            return ChatColor.RED + "Go!";
        return null;
    }


}
