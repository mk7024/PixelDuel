package main.mk7024.Task;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WaitingTitle extends BukkitRunnable {
    private int i = 0;
    Player player;
    public WaitingTitle(Player player){
        this.player = player;
    }
    @Override
    public void run(){
        player.sendTitle(ChatColor.translateAlternateColorCodes('&',"&a&l游戏等待中"),ChatColor.AQUA + getSymbol(i),0,40,0);
        i++;
    }

    public String getSymbol(int m){
        if(m == 0){
            return "|";
        }
        if(m == 1){
            return "\\";
        }
        if(m==2){
            return "-";
        }
        if(m==3){
            i = -1;
            return "/";
        }
        return null;
    }
}