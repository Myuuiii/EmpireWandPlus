package com.myuuiii.empirewandplus.SpellEffects.Fireball;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEffects.SpellEffect;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import com.myuuiii.empirewandplus.Data.SpellNames;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;

import java.util.ArrayList;

public class FireballEffect extends SpellEffect {

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.Fireball);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        entity.getWorld().spawnParticle(Particle.FLAME, entity.getLocation(), 50, 1, 1, 1, 0.2);
        entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, entity.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
        entity.getWorld().spawnParticle(Particle.LAVA, entity.getLocation(), 50, 1, 1, 1, 0.2);

        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_DROWNED_DEATH_WATER, 1, 0.85f);
    }

    @Override
    public void OnDeath(Entity entity, Spell spell) {
        entity.getWorld().createExplosion(entity.getLocation(), 10, true);
    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.FIREBALL_ENTITIES;
    }
}
