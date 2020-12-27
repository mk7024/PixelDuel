package main.mk7024;

import main.mk7024.Game.Game;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Duel extends JavaPlugin {
    private static Duel plugin;
    private Set<Game> games = new HashSet<>();

    @Override
    public void onEnable(){
        saveDefaultConfig();
        ConfigurationSection config = this.getConfig().getConfigurationSection("Game");
        if(config != null){
            for(String key : config.getKeys(true)){
                Game game = new Game(key);
            }
        }else{
            this.getLogger().info("未加载游戏");
        }
        plugin = this;
    }

    @Override
    public void onDisable(){

    }

    public static Duel getPlugin(){
        return plugin;
    }

}
