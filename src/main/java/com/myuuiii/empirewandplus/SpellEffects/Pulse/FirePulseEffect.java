package com.myuuiii.empirewandplus.SpellEffects.Pulse;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getFirework;
import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class FirePulseEffect {

    public static void Execute(Entity s) {

        Spell spell = EmpireWandPlus.spellHashMap.get(SpellNames.FirePulse);

        new BukkitRunnable() {
            public void run() {
                if (SpellEntityLists.FIRE_PULSE_ENTITIES.contains(s)) {
                    if (s.isDead()) {
                        // Executed when the entity is destroyed
                        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), s);
                        for (final Entity en : near)
                            if (en instanceof LivingEntity targetEntity)
                                targetEntity.damage(spell.getDamage());
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    launchFirework(s);
                    s.getWorld().spawnParticle(Particle.FLAME, s.getLocation(), 50, 1, 1, 1, 0.1);
                }
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);
    }

    private static void launchFirework(Entity s) {
        Firework fw = getFirework(s);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(244, 150, 0))
                .withFlicker()
                .build());
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(0, 0, 0))
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
