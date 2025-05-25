package com.myuuiii.empirewandplus.Spells;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class ArrowRain extends Spell {
    private final Random random = new Random();
    
    @Override
    public int getMaxReach() {
        return 12;
    }

    @Override
    public double getInRangeDistance() {
        return 2;
    }

    @Override
    public double getDamage() {
        return 10;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        World world = loc.getWorld();
        if (world == null) return;
        
        EmpireWandPlus plugin = JavaPlugin.getPlugin(EmpireWandPlus.class);
        
        new BukkitRunnable() {
            int arrowsSpawned = 0;
            final int maxArrows = 75;
            
            @Override
            public void run() {
                if (arrowsSpawned >= maxArrows) {
                    this.cancel();
                    return;
                }
                
                for (int i = 0; i < 3; i++) { // Spawn 3 arrows per tick
                    if (arrowsSpawned >= maxArrows) break;
                    
                    double offsetX = random.nextDouble() * 10 - 5; // -5 to 5
                    double offsetZ = random.nextDouble() * 10 - 5; // -5 to 5
                    
                    Location arrowLoc = loc.clone().add(offsetX, 15, offsetZ);
                    Arrow arrow = world.spawnArrow(arrowLoc, new Vector(0, -1, 0), 1.5f, 0);
                    arrow.setShooter(p);
                    arrow.setCritical(false);
                    arrow.setDamage(2); // Reduced damage since there are many arrows
                    arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
                    
                    addArrowTrail(arrow, plugin);
                    
                    arrowsSpawned++;
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(EmpireWandPlus.class), 0L, 2L); // Spawn arrows every 2 ticks
        
        // Create initial spell effect at the cast location
        world.spawnParticle(Particle.INSTANT_EFFECT, loc, 20, 2, 0.5, 2, 0.1);
        world.spawnParticle(Particle.EFFECT, loc, 15, 1.5, 0.5, 1.5, 0.1);
        world.spawnParticle(Particle.CLOUD, loc.clone().add(0, 15, 0), 30, 3, 1, 3, 0.05);
    }
    
    private void addArrowTrail(Arrow arrow, EmpireWandPlus plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isDead() || arrow.isOnGround()) {
                    this.cancel();
                    // Create potion splash effect when arrow lands
                    arrow.getWorld().spawnParticle(Particle.INSTANT_EFFECT, arrow.getLocation(), 15, 0.3, 0.3, 0.3, 0.05);
                    arrow.getWorld().spawnParticle(Particle.EFFECT, arrow.getLocation(), 10, 0.2, 0.2, 0.2, 0.1);
                    
                    if (!arrow.isDead())
                        arrow.remove();
                    return;
                }
                
                // Create potion-like trailing particle effects
                arrow.getWorld().spawnParticle(Particle.INSTANT_EFFECT, arrow.getLocation(), 3, 0.05, 0.05, 0.05, 0.01);
                arrow.getWorld().spawnParticle(Particle.EFFECT, arrow.getLocation(), 1, 0.05, 0.05, 0.05, 0.01);
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    @Override
    public String getConfigName() {
        return "arrowRain";
    }
}
