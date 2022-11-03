package moe.myuuiii.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

import static moe.myuuiii.empirewandplus.Extensions.GetNearbyEntities;
import static moe.myuuiii.empirewandplus.generators.FireworkGenerator.getFirework;

public class FireCometSpellEffect {

    //
    // Settings
    //
    private static double _closeRange = 10;
    private static double _damage = 6;
    private static int _fireTicks = 50;

    public static void Execute(Snowball s) {
        new BukkitRunnable() {
            public void run() {
                if (Data.firecomets.contains(s)) {
                    if (s.isDead()) {
                        // Executed when the entity is destroyed
                        s.getWorld().createExplosion(s.getLocation(), 3, false);

                        final List<Entity> near = GetNearbyEntities(_closeRange, s);
                        for (final Entity en : near) {
                            if (en instanceof LivingEntity targetEntity) {
                                targetEntity.damage(_damage);
                                targetEntity.setFireTicks(_fireTicks);
                            }
                        }
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    launchFirework(s);
//                    s.getWorld().spawnParticle(Particle.FLAME, s.getLocation(), 250, 1, 1, 1, 0.05);
                }
            }
        }.runTaskTimer(App._app, 0L, 1L);
    }

    private static void launchFirework(Snowball s) {
        Firework fw = getFirework(s);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(244, 150, 0))
                .withFlicker()
                        .with(FireworkEffect.Type.BALL_LARGE)
                .build());
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(0, 0, 0))
                .with(FireworkEffect.Type.BALL_LARGE)
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
