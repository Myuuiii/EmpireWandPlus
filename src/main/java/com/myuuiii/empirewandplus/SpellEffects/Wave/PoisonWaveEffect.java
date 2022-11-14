package com.myuuiii.empirewandplus.SpellEffects.Wave;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEffects.SpellEffect;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import com.myuuiii.empirewandplus.Data.SpellNames;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getFirework;
import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class PoisonWaveEffect extends SpellEffect {
    //
    // Settings
    //
    private static int _poisonDuration = 100;

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.PoisonWave);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        launchFirework(entity);
        entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_AZALEA_BREAK, 1, 0.65f);

        final List<Entity> near = getNearbyEntities(spell.getCloseRange(), entity);
        ;
        for (final Entity en : near) {
            if (!(en instanceof LivingEntity targetEntity))
                return;
            if (targetEntity instanceof Player p) {
                if (SpellEntityLists.POISON_WAVE_PLAYERS.contains(p.getUniqueId()))
                    continue;
            }
            targetEntity.addPotionEffect(
                    new PotionEffect(PotionEffectType.POISON, _poisonDuration, 1, true, false));
        }
    }

    @Override
    public void OnDeath(Entity entity, Spell spell) {

    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.POISON_WAVE_ENTITIES;
    }

    private static void launchFirework(Entity e) {
        Firework fw = getFirework(e);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(20, 150, 0))
                .withFade(Color.fromRGB(0, 0, 0))
                .build()
        );
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(0, 0, 0))
                .withFade(Color.fromRGB(20, 150, 0))
                .build()
        );
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
