package main.mk7024.Utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class getLastDamager {
    public static Entity main(Entity entity) {
        EntityDamageEvent event = entity.getLastDamageCause();
        if (event != null && !event.isCancelled() && (event instanceof EntityDamageByEntityEvent)) {
            Entity damager = ((EntityDamageByEntityEvent) event).getDamager();
            if (damager instanceof Projectile) {
                Object shooter = ((Projectile) damager).getShooter();
                if (shooter != null && (shooter instanceof Entity)) return (Entity) shooter;
            }

            // Add other special cases if necessary
            return damager;
        }


        return null;
    }
}
