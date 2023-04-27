package com.myuuiii.empirewandplus.Listeners;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Extensions;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class FallDamageListener implements Listener {
    @EventHandler
    public void fall(EntityDamageEvent e) {
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();
        if (SpellEntityLists.LEAP_PLAYERS.contains(p.getUniqueId())) {
            e.setCancelled(true);
            SpellEntityLists.LEAP_PLAYERS.remove(p.getUniqueId());
            p.getLocation().add(0, 1, 0).getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 100, 0, 0, 0, 0.1);
        }
    }

    @EventHandler
    public void move(PlayerMoveEvent moveEvent){
        if (SpellEntityLists.LEAP_PLAYERS.contains(moveEvent.getPlayer().getUniqueId()) && Extensions.PlayerIsOnGround(moveEvent.getPlayer())) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    SpellEntityLists.LEAP_PLAYERS.remove(moveEvent.getPlayer().getUniqueId());
                }
            }.runTaskLater(EmpireWandPlus._plugin, 5);
        }
    }
}
