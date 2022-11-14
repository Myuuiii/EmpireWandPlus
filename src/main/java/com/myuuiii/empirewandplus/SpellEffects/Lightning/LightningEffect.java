package com.myuuiii.empirewandplus.SpellEffects.Lightning;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getFirework;
import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class LightningEffect {
    //
    // Settings
    //
    private static int _explosionSize = 3;

    public static void Execute(Entity s) {

        Spell spell = EmpireWandPlus.spellHashMap.get(SpellNames.Lightning);

        new BukkitRunnable() {
            public void run() {
                if (SpellEntityLists.LIGHTNING_ENTITIES.contains(s)) {
                    if (s.isDead()) {
                        // Executed when the entity is destroyed
                        s.getWorld().strikeLightning(s.getLocation());
                        s.getWorld().createExplosion(s.getLocation(), _explosionSize);
                        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), s);
                        ;
                        for (final Entity en : near) {
                            if (en instanceof Damageable targetEntity)
                                targetEntity.damage(spell.getDamage());
                        }
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    launchFirework(s);
                    s.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, s.getLocation(), 250, 0.1, 0.1, 0.1, 0.1);
                }
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);

    }

    private static void launchFirework(Entity e) {
        Firework fw = getFirework(e);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromBGR(255, 255, 255))
                .withTrail()
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
