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
            String name = player.getName();
            int kills = Duel.getSql().getData(name,"kills");
            int death = Duel.getSql().getData(name,"death");
            double kd = (double)kills/death;
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(3);
            nf.setRoundingMode(RoundingMode.UP);
            player.sendMessage(ChatColor.GRAY + "============" + ChatColor.RED + "像素决斗" + ChatColor.GRAY + "============");
            player.sendMessage(ChatColor.AQUA + "ID: " + ChatColor.RED + name);
            player.sendMessage(ChatColor.AQUA + "击杀: " + ChatColor.RED + kills);
            player.sendMessage(ChatColor.AQUA + "死亡: " + ChatColor.RED + death);
            player.sendMessage(ChatColor.AQUA + "游戏局数: " + ChatColor.RED + Duel.getSql().getData(name,"gameplay"));
            player.sendMessage(ChatColor.AQUA + "KD: " + ChatColor.RED + nf.format(kd));
            return true;
        }
        return false;
    }
}
