package com.myuuiii.empirewandplus.SpellEffects.Smite;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class SmiteEffect {
    //
    // Settings
    //
    private static int _explosionSize = 10;
    private static int _lightningStrikeRadius = 5;

    public static void Execute(Entity s) {

        Spell spell = EmpireWandPlus.spellHashMap.get(SpellNames.Smite);

        new BukkitRunnable() {
            public void run() {
                if (SpellEntityLists.SMITE_ENTITIES.contains(s)) {
                    if (s.isDead()) {
                        // Executed when the entity is destroyed
                        for (Integer x = -_lightningStrikeRadius; x <= _lightningStrikeRadius; x++) {
                            for (Integer z = -_lightningStrikeRadius; z <= _lightningStrikeRadius; z++) {
                                s.getWorld().strikeLightning(s.getLocation().add(x, 0, z));
                            }
                        }

                        s.getWorld().createExplosion(s.getLocation(), _explosionSize, true);

                        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), s);
                        for (final Entity en : near) {
                            if (en instanceof Damageable targetEntity)
                                targetEntity.damage(spell.getDamage());
                        }
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    s.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, s.getLocation(), 75, 0.1, 0.1, 0.1, 0.1);
                    s.getWorld().spawnParticle(Particle.CLOUD, s.getLocation(), 75, 0.1, 0.1, 0.1, 0.2);
                    s.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, s.getLocation(), 75, 0.1, 0.1, 0.1, 0.1);
                }
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);

    }
}
