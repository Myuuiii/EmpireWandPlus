package com.myuuiii.empirewandplus.SpellEffects.Smite;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Abstracts.ProjectileSpellEffect;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import com.myuuiii.empirewandplus.Data.SpellNames;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class SmiteEffect extends ProjectileSpellEffect {
    //
    // Settings
    //
    private static int _explosionSize = 10;
    private static int _lightningStrikeRadius = 5;

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.Smite);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        entity.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, entity.getLocation(), 75, 0.1, 0.1, 0.1, 0.1);
        entity.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 75, 0.1, 0.1, 0.1, 0.2);
        entity.getWorld().spawnParticle(Particle.ENCHANT, entity.getLocation(), 75, 0.1, 0.1, 0.1, 0.1);
    }

    @Override
    public void OnDeath(Entity entity, Spell spell) {
        for (int x = -_lightningStrikeRadius; x <= _lightningStrikeRadius; x++) {
            for (int z = -_lightningStrikeRadius; z <= _lightningStrikeRadius; z++) {
                entity.getWorld().strikeLightning(entity.getLocation().add(x, 0, z));
            }
        }

        entity.getWorld().createExplosion(entity.getLocation(), _explosionSize, true);

        final List<Entity> near = getNearbyEntities(spell.getInRangeDistance(), entity);
        for (final Entity en : near) {
            if (en instanceof Damageable targetEntity)
                targetEntity.damage(spell.getDamage());
        }
    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.SMITE_ENTITIES;
    }
}
