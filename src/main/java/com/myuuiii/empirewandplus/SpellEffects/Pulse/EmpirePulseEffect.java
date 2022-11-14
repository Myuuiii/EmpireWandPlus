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

public class EmpirePulseEffect {
    public static void Execute(Entity e) {

        Spell spell = EmpireWandPlus.spellHashMap.get(SpellNames.EmpirePulse);

        new BukkitRunnable() {
            public void run() {
                if (SpellEntityLists.EMPIRE_PULSE_ENTITIES.contains(e)) {
                    if (e.isDead()) {
                        // Executed when the entity is destroyed
                        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), e);
                        for (final Entity en : near)
                            if (en instanceof LivingEntity targetEntity)
                                targetEntity.damage(spell.getDamage());
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    launchFirework(e);
                    e.getWorld().spawnParticle(Particle.SPELL_WITCH, e.getLocation(), 250, 1, 1, 1, 0.2);
                    e.getWorld().spawnParticle(Particle.SMOKE_LARGE, e.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
                }
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);
    }

    private static void launchFirework(Entity e) {
        Firework fw = getFirework(e);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(200, 0, 230))
                .withFade(Color.fromRGB(0, 0, 0))
                .with(FireworkEffect.Type.BALL)
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
