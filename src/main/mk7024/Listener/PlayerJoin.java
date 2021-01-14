package main.mk7024.Listener;

import main.mk7024.Duel;
import main.mk7024.Kit;
import main.mk7024.Lobby;
import main.mk7024.Player.PlayerState;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.setExp(0);
        if(!player.hasPlayedBefore()){
            if(!Duel.getSql().isInDataBase(player)){
                Duel.getSql().addData(player.getUniqueId());
            }
        }
        Kit.setLobbyItem(player);
        sendJoinMessage(player);
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',"&6[&2+&6] &9"+ player.getName() + "&6加入了对决."));
        Duel.getPlayerManager().addPlayer(player);
        Duel.getLobby().teleportToLobby(player);
    }


    public void sendJoinMessage(Player player){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7=====&r&a&l欢迎来到像素对决&r&7====="));
        player.sendMessage(ChatColor.RED + "输入/queue随机匹配一位队友");
        player.sendMessage(ChatColor.RED + "输入/stats查看战绩");
        player.sendMessage(ChatColor.RED + ChatColor.translateAlternateColorCodes('&',"&7=====&r&a&l欢迎加入网易国服群:xxx&r&7====="));
    }
}
