package com.myuuiii.empirewandplus.SpellEffects.Pulse;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Abstracts.SpellEffect;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import com.myuuiii.empirewandplus.Data.SpellNames;
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

public class EmpirePulseEffect extends SpellEffect {

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.EmpirePulse);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        launchFirework(entity);
        entity.getWorld().spawnParticle(Particle.SPELL_WITCH, entity.getLocation(), 250, 1, 1, 1, 0.2);
        entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, entity.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
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
        return SpellEntityLists.EMPIRE_PULSE_ENTITIES;
    }

    private static void launchFirework(Entity e) {
        Firework fw = getFirework(e);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.setPower(0);
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(200, 0, 230))
                .withFade(Color.fromRGB(0, 0, 0))
                .with(FireworkEffect.Type.BALL)
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
