package com.myuuiii.empirewandplus.SpellEffects.Comet;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEffects.SpellEffect;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getFirework;
import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class FireCometEffect extends SpellEffect {
    //
    // Settings
    //
    private static int _fireTicks = 50;

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.FireComet);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        launchFirework(entity);
    }

    @Override
    public void OnDeath(Entity entity, Spell spell) {
        entity.getWorld().createExplosion(entity.getLocation(), 3, false);

        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), entity);
        for (final Entity en : near) {
            if (en instanceof LivingEntity targetEntity) {
                targetEntity.damage(spell.getDamage());
                targetEntity.setFireTicks(_fireTicks);
            }
        }
    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.FIRE_COMET_ENTITIES;
    }

    private static void launchFirework(Entity s) {
        Firework fw = getFirework(s);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(244, 150, 0))
                .withFlicker()
                .with(FireworkEffect.Type.BALL_LARGE)
                .build());
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(0, 0, 0))
                .with(FireworkEffect.Type.BALL_LARGE)
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
