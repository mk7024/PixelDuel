package main.mk7024.Listener;

import main.mk7024.Duel;
import main.mk7024.Game.Game;
import main.mk7024.Game.GameManager;
import main.mk7024.Kit;
import main.mk7024.Player.PlayerState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player deader = event.getEntity();
        if(!PlayerState.notInGame(deader)){
            Entity killer = main.mk7024.Utils.getLastDamager.main(deader);
            if(killer instanceof Player){
                Duel.getPlayerManager().setInLobby(deader);
                Game game = Duel.getGameManager().getPlayerInWhichGame(deader);
                game.setWinner((Player)killer);
                game.removePlayer(deader);
                Duel.getSql().setData(deader.getName(),"death");
                Duel.getSql().setData(deader.getName(),"gameplay");
                Kit.setLobbyItem(deader);
            }
        }
    }
}
