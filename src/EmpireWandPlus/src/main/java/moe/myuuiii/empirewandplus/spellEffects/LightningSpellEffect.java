package moe.myuuiii.empirewandplus.spellEffects;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static moe.myuuiii.empirewandplus.Extensions.getNearbyEntities;
import static moe.myuuiii.empirewandplus.generators.FireworkGenerator.getFirework;

public class LightningSpellEffect {

    //
    // Settings
    //
    private static double _closeRange = 6;
    private static double _damage = 6;
    private static int _explosionSize = 3;

    public static void Execute(Snowball s) {
        new BukkitRunnable() {
            public void run() {
                if (Data.lightningBolts.contains(s)) {
                    if (s.isDead()) {
                        // Executed when the entity is destroyed
                        s.getWorld().strikeLightning(s.getLocation());
                        s.getWorld().createExplosion(s.getLocation(), _explosionSize);
                        final List<Entity> near = getNearbyEntities(_closeRange, s);;
                        for (final Entity en : near) {
                            if (en instanceof Damageable targetEntity)
                                targetEntity.damage(_damage);
                        }
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    launchFirework(s);
                    s.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, s.getLocation(), 250, 0.1, 0.1, 0.1, 0.1);
                }
            }
        }.runTaskTimer(App._app, 0L, 1L);

    }

    private static void launchFirework(Snowball s) {
        Firework fw = getFirework(s);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromBGR(255, 255, 255))
                .withTrail()
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
