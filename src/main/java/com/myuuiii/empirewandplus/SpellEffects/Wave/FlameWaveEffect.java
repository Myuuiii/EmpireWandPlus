package com.myuuiii.empirewandplus.SpellEffects.Wave;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class FlameWaveEffect {
    //
    // Settings
    //
    private static int _fireTickDuration = 300;

    public static void Execute(Entity s) {

        Spell spell = EmpireWandPlus.spellHashMap.get(SpellNames.FlameWave);

        new BukkitRunnable() {
            public void run() {
                if (SpellEntityLists.FLAME_WAVE_ENTITIES.contains(s)) {
                    if (s.isDead()) {
                        // Executed when the entity is destroyed
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    s.getWorld().spawnParticle(Particle.FLAME, s.getLocation(), 125, 0.5, 0.5, 0.5, 0.1);
                    s.getWorld().spawnParticle(Particle.SMOKE_LARGE, s.getLocation(), 125, 0.5, 0.5, 0.5, 0.1);

                    s.getWorld().playSound(s.getLocation(), Sound.ENTITY_DROWNED_DEATH, 1, 0.65f);

                    final List<Entity> near = getNearbyEntities(spell.getCloseRange(), s);
                    for (final Entity en : near) {
                        if (!(en instanceof LivingEntity targetEntity))
                            return;

                        if (targetEntity instanceof Player p) {
                            if (SpellEntityLists.FLAME_WAVE_PLAYERS.contains(p.getUniqueId()))
                                continue;
                        }

                        targetEntity.setFireTicks(_fireTickDuration);

                    }
                }
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);
    }
}
