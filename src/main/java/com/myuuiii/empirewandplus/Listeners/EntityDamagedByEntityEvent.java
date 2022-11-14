package com.myuuiii.empirewandplus.Listeners;

import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamagedByEntityEvent implements Listener {
    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Firework) {
            Firework fw = (Firework) e.getDamager();
            if (fw.hasMetadata("nodamage")) {
                e.setCancelled(true);
            }
        }
    }
}
