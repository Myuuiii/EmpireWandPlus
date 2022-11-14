package com.myuuiii.empirewandplus.SpellEffects.Lightning;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEffects.SpellEffect;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getFirework;
import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class LightningEffect extends SpellEffect {
    //
    // Settings
    //
    private static int _explosionSize = 3;

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.Lightning);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        launchFirework(entity);
        entity.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, entity.getLocation(), 250, 0.1, 0.1, 0.1, 0.1);
    }

    @Override
    public void OnDeath(Entity entity, Spell spell) {
        entity.getWorld().strikeLightning(entity.getLocation());
        entity.getWorld().createExplosion(entity.getLocation(), _explosionSize);
        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), entity);
        for (final Entity en : near) {
            if (en instanceof Damageable targetEntity)
                targetEntity.damage(spell.getDamage());
        }
    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.LIGHTNING_ENTITIES;
    }

    private static void launchFirework(Entity e) {
        Firework fw = getFirework(e);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromBGR(255, 255, 255))
                .withTrail()
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
