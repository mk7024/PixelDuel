package main.mk7024.Command;

import main.mk7024.Duel;
import main.mk7024.Lobby;
import main.mk7024.Player.PlayerState;
import main.mk7024.Task.CheckingQueue;
import main.mk7024.Task.WaitingTitle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class queue implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args){
        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        if(command.getName().equalsIgnoreCase("queue")){
            if(!PlayerState.canJoin(player)){
                player.sendMessage(ChatColor.RED + "你已经加入游戏了!");
                return true;
            }
            if(Duel.getGameManager().getAllgame().size() == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&l未加载游戏!请联系管理员"));
                return true;
            }
            CheckingQueue.checkQueue();
            BukkitRunnable waitingTitle = new WaitingTitle(player);
            waitingTitle.runTaskTimer(Duel.getPlugin(),0,20);
            Lobby.addToQueue(player);
            return true;
        }
        return false;
    }
}
