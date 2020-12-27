package main.mk7024;

import main.mk7024.Game.Game;
import main.mk7024.Game.GameManager;
import main.mk7024.Player.PlayerManager;
import main.mk7024.Task.CheckingQueue;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class Duel extends JavaPlugin {
    private static Duel plugin;
    private Set<Game> games = new HashSet<>();
    private static GameManager gameManager;
    private static PlayerManager playerManager;

    @Override
    public void onEnable(){
        plugin = this;
        saveDefaultConfig();
        ConfigurationSection config = this.getConfig().getConfigurationSection("Game");
        if(config != null){
            for(String key : config.getKeys(true)){
                Game game = new Game(key);
                gameManager.getAllgame().add(game);
            }
            BukkitRunnable checkingQueue = new CheckingQueue();
            checkingQueue.runTaskTimer(this,0,100); //每5秒循环一次
        }else{
            this.getLogger().info("未加载游戏");
        }
        getCommand("queue").setExecutor(new main.mk7024.Command.queue());
    }

    @Override
    public void onDisable(){

    }

    public static Duel getPlugin(){
        return plugin;
    }

    public static GameManager getGameManager(){
        return gameManager;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }
}
