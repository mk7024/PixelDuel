package main.mk7024.Game;

import net.minecraft.server.v1_12_R1.GameRules;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldRule {
    public void setDefaultRule(String name){
        World w = Bukkit.getWorld(name);
    }
}
