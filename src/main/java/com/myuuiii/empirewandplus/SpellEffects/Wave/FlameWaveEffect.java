package com.myuuiii.empirewandplus.SpellEffects.Wave;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Abstracts.SpellEffect;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import com.myuuiii.empirewandplus.Data.SpellNames;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class FlameWaveEffect extends SpellEffect {
    //
    // Settings
    //
    private static int _fireTickDuration = 300;

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.FlameWave);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        entity.getWorld().spawnParticle(Particle.FLAME, entity.getLocation(), 125, 0.5, 0.5, 0.5, 0.1);
        entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, entity.getLocation(), 125, 0.5, 0.5, 0.5, 0.1);

        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_DROWNED_DEATH, 1, 0.65f);

        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), entity);
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

    @Override
    public void OnDeath(Entity entity, Spell spell) {

    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.FLAME_WAVE_ENTITIES;
    }
}
