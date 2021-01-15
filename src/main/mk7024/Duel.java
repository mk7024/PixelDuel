package main.mk7024;

import main.mk7024.Game.Game;
import main.mk7024.Game.GameManager;
import main.mk7024.Listener.*;
import main.mk7024.Player.PlayerManager;
import main.mk7024.Task.CheckingQueue;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Duel extends JavaPlugin {
    private static Duel plugin;
    private static GameManager gameManager;
    private static PlayerManager playerManager;
    private static Sql sql;
    private static Lobby lobby;

    @Override
    public void onEnable() {
        plugin = this;
        lobby = new Lobby();
        sql = new Sql();
        playerManager = new PlayerManager();
        gameManager = new GameManager();
        saveDefaultConfig();
        ConfigurationSection config = this.getConfig().getConfigurationSection("Game");
        if (config != null) {
            for (String key : config.getKeys(false)) {
                try {
                    System.out.println(key);
                    Game game = new Game(key);
                    gameManager.getAllgame().add(game);
                } catch (Exception error) {
                    getLogger().warning("游戏加载失败,请检查配置文件是否完好!");
                }
            }
        } else {
            this.getLogger().info("未加载游戏");
        }
        BukkitRunnable checkingQueue = new CheckingQueue();
        checkingQueue.runTaskTimer(this, 0, 140); //每7秒循环一次
        getCommand("queue").setExecutor(new main.mk7024.Command.queue());
        getCommand("stats").setExecutor(new main.mk7024.Command.stats());
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new Block(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInLobby(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new ItemSpawn(), this);
        sql.establishConnection();
        sql.insertSQL();
        sql.disConnect();
    }

    @Override
    public void onDisable() {
        sql.disConnect();
    }

    public static Sql getSql() {
        return sql;
    }

    public static Duel getPlugin() {
        return plugin;
    }

    public static Lobby getLobby() {
        return lobby;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }


}
