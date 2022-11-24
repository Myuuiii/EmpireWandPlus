package com.myuuiii.empirewandplus.SpellEffects.Cloud;

import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class KajCloudEffect extends BukkitRunnable {

    @Override
    public void run() {
        for (UUID uuid : SpellEntityLists.KAJ_CLOUD_PLAYERS) {
            if (Bukkit.getPlayer(uuid) != null) {
                Player p = Bukkit.getPlayer(uuid);
                assert p != null;
                Location l = p.getLocation();
                l.add(0, 0.1, 0);
                //
                // Settings
                //
                int _particleDensityModifier = 3;
                p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 10 * _particleDensityModifier, 0.3, 0.0,
                        0.3,
                        0);
                p.getWorld().spawnParticle(Particle.CLOUD, l, 10 * _particleDensityModifier, 0.3, 0.0, 0.3, 0);
            }
        }
    }
}
