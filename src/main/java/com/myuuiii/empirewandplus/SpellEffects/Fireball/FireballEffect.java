package com.myuuiii.empirewandplus.SpellEffects.Fireball;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class FireballEffect {
    public static void Execute(Entity e) {

        Spell spell = EmpireWandPlus.spellHashMap.get(SpellNames.Fireball);

        new BukkitRunnable() {
            public void run() {
                if (SpellEntityLists.FIREBALL_ENTITIES.contains(e)) {
                    if (e.isDead()) {
                        // Executed when the entity is destroyed
                        e.getWorld().createExplosion(e.getLocation(), 10, true);
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    e.getWorld().spawnParticle(Particle.FLAME, e.getLocation(), 50, 1, 1, 1, 0.2);
                    e.getWorld().spawnParticle(Particle.SMOKE_LARGE, e.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
                    e.getWorld().spawnParticle(Particle.LAVA, e.getLocation(), 50, 1, 1, 1, 0.2);

                    e.getWorld().playSound(e.getLocation(), Sound.ENTITY_DROWNED_DEATH_WATER, 1, 0.85f);
                }
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);
    }
}
