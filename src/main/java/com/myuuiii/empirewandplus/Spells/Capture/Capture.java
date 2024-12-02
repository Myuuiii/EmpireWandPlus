package com.myuuiii.empirewandplus.Spells.Capture;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Capture extends Spell {
    @Override
    public int getReach() {
        return 8;
    }

    @Override
    public double getCloseRange() {
        return 3;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (entity.equals(executingPlayer)) return;
        executingPlayer.addPassenger(entity);
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, loc, 250, 1, 1, 1, 0.1);
        p.getWorld().spawnParticle(Particle.LARGE_SMOKE, loc, 100, 1, 1, 1, 0);

        p.getWorld().playSound(loc, Sound.ENTITY_PIG_SADDLE, 5, 0.85f);
        p.getWorld().playSound(loc, Sound.ENTITY_SHULKER_TELEPORT, 5, 1f);

        if (!p.getPassengers().isEmpty()) {
            for (Entity passenger : p.getPassengers()) {
                passenger.leaveVehicle();
                if (passenger instanceof Player) {

                    // Apply velocity with a progressive scaling factor to ensure proper application
                    Bukkit.getScheduler().runTaskTimer(EmpireWandPlus._plugin, new Runnable() {
                        int count = 0;

                        @Override
                        public void run() {
                            if (count >= 5) { // Adjust velocity application over several ticks
                                Bukkit.getScheduler().cancelTask(this.hashCode());
                                return;
                            }
                            Vector direction = p.getLocation().getDirection().normalize().multiply(0.4 * (count + 1));
                            passenger.setVelocity(direction);
                            count++;
                        }
                    }, 1L, 1L);
                }
                else {
                    Bukkit.getScheduler().runTaskLater(EmpireWandPlus._plugin, () -> {
                        Vector direction = p.getLocation().getDirection().normalize().multiply(2);
                        passenger.setVelocity(direction);
                    }, 1L); // Delay by 1 tick by 1 tick
                }
            }
        }
    }
}
