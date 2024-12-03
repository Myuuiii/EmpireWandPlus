package com.myuuiii.empirewandplus.SpellEffects.Wave;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Abstracts.ProjectileSpellEffect;
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

public class BloodWaveProjectileEffect extends ProjectileSpellEffect {
    //
    // Settings
    //
    private static int _witherDuration = 100;

    @Override
    public Spell getSpell() {
        return EmpireWandPlus.spellHashMap.get(SpellNames.BloodWave);
    }

    @Override
    public void WhileAlive(Entity entity, Spell spell) {
        launchFirework(entity);

        entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_AZALEA_BREAK, 1, 0.65f);
        entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_AZALEA_LEAVES_BREAK, 1, 0.65f);

        final List<Entity> near = getNearbyEntities(spell.getInRangeDistance(), entity);
        for (final Entity en : near) {
            if (!(en instanceof LivingEntity targetEntity))
                return;

            if (targetEntity instanceof Player player) {
                if (SpellEntityLists.BLOOD_WAVE_PLAYERS.contains(player.getUniqueId()))
                    continue;
            }

            targetEntity.addPotionEffect(
                    new PotionEffect(PotionEffectType.WITHER, _witherDuration, 1, true, false));
        }
    }

    @Override
    public void OnDeath(Entity entity, Spell spell) {

    }

    @Override
    public ArrayList<Entity> getSpellEntityList() {
        return SpellEntityLists.BLOOD_WAVE_ENTITIES;
    }

    private static void launchFirework(Entity s) {
        Firework fw = getFirework(s);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.setPower(0);
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(255, 0, 0))
                .withFade(Color.BLACK)
                .with(FireworkEffect.Type.BALL)
                .build()
        );
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(75, 0, 0))
                .withFade(Color.fromRGB(255, 0, 0))
                .with(FireworkEffect.Type.BALL)
                .build()
        );
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
