package com.myuuiii.empirewandplus.SpellEffects.Pulse;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEffects.SpellEffect;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getFirework;
import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class FirePulseEffect extends SpellEffect {

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.FirePulse);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        launchFirework(entity);
        entity.getWorld().spawnParticle(Particle.FLAME, entity.getLocation(), 50, 1, 1, 1, 0.1);
    }

    @Override
    public void OnDeath(Entity entity, Spell spell) {
        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), entity);
        for (final Entity en : near)
            if (en instanceof LivingEntity targetEntity)
                targetEntity.damage(spell.getDamage());
    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.FIRE_PULSE_ENTITIES;
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
