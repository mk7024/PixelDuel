package main.mk7024.Player;

import main.mk7024.Duel;
import org.bukkit.ChatColor;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.UUID;

public class GamePlayer {
    int kills;
    int death;
    int gameplay;
    org.bukkit.entity.Player player;
    PlayerState playerState;
    UUID uuid;
    public GamePlayer(org.bukkit.entity.Player player){
        this.player = player;
        this.uuid = player.getUniqueId();
        this.kills = Duel.getSql().getData(uuid,"kills");
        this.death = Duel.getSql().getData(uuid,"death");
        this.gameplay = Duel.getSql().getData(uuid,"gameplay");
        this.playerState = PlayerState.INLOBBY;
    }

    public void refreshData(){
        this.kills = Duel.getSql().getData(uuid,"kills");
        this.death = Duel.getSql().getData(uuid,"death");
        this.gameplay = Duel.getSql().getData(uuid,"gameplay");
    }

    public UUID getUUID() {
        return uuid;
    }

    public PlayerState getState(){
        return playerState;
    }

    public void setState(PlayerState playerState){
        this.playerState = playerState;
    }

    public UUID getUniqueId(){
        return player.getUniqueId();
    }

    public void showStats(){
        double kd = (double)kills/death;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(3);
        nf.setRoundingMode(RoundingMode.UP);
        player.sendMessage(ChatColor.GRAY + "============" + ChatColor.RED + "像素决斗" + ChatColor.GRAY + "============");
        player.sendMessage(ChatColor.AQUA + "ID: " + ChatColor.RED + player.getName());
        player.sendMessage(ChatColor.AQUA + "击杀: " + ChatColor.RED + kills);
        player.sendMessage(ChatColor.AQUA + "死亡: " + ChatColor.RED + death);
        player.sendMessage(ChatColor.AQUA + "游戏局数: " + ChatColor.RED + gameplay);
        player.sendMessage(ChatColor.AQUA + "KD: " + ChatColor.RED + nf.format(kd));
    }
}
