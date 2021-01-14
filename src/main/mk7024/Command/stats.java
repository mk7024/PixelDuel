package main.mk7024.Command;

import main.mk7024.Duel;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class stats implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command,String lable,String[] args){
        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player)sender;
        if(command.getName().equalsIgnoreCase("stats")){
            Duel.getPlayerManager().getGamePlayer(player).showStats();
            return true;
        }
        return false;
    }
}
