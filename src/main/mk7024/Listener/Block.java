package main.mk7024.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Block implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(event.getPlayer().isOp()){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(event.getPlayer().isOp()){
            return;
        }
        event.setCancelled(true);
    }
}
