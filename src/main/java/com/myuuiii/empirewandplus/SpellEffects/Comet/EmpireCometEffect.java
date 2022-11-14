package com.myuuiii.empirewandplus.SpellEffects.Comet;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEffects.SpellEffect;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import com.myuuiii.empirewandplus.Data.SpellNames;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getFirework;
import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class EmpireCometEffect extends SpellEffect {
    //
    // Settings
    //
    private static int _blindnessDuration = 50;

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.EmpireComet);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        launchFirework(entity);
        entity.getWorld().spawnParticle(Particle.SPELL_WITCH, entity.getLocation(), 250, 1, 1, 1, 0.2);
        entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, entity.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
    }

    @Override
    public void OnDeath(Entity entity, Spell spell) {
        entity.getWorld().createExplosion(entity.getLocation(), 10, false);

        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), entity);
        for (final Entity en : near) {
            if (en instanceof LivingEntity targetEntity) {
                targetEntity.damage(spell.getDamage());
                targetEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,
                        _blindnessDuration, 1, true, false));
            }
        }
    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.EMPIRE_COMET_ENTITIES;
    }

    private static void launchFirework(Entity e) {
        Firework fw = getFirework(e);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(200, 0, 230))
                .withFade(Color.fromRGB(0, 0, 0))
                .with(FireworkEffect.Type.STAR)
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
