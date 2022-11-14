package com.myuuiii.empirewandplus.SpellEffects.Wave;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellNames;
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getFirework;
import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class BloodWaveEffect {
    //
    // Settings
    //
    private static int _witherDuration = 100;

    public static void Execute(Entity e) {

        Spell spell = EmpireWandPlus.spellHashMap.get(SpellNames.BloodWave);

        new BukkitRunnable() {
            public void run() {
                if (SpellEntityLists.BLOOD_WAVE_ENTITIES.contains(e)) {
                    if (e.isDead()) {
                        // Executed when the entity is destroyed
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    launchFirework(e);

                    e.getWorld().playSound(e.getLocation(), Sound.BLOCK_AZALEA_BREAK, 1, 0.65f);
                    e.getWorld().playSound(e.getLocation(), Sound.BLOCK_AZALEA_LEAVES_BREAK, 1, 0.65f);

                    final List<Entity> near = getNearbyEntities(spell.getCloseRange(), e);
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
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);
    }

    private static void launchFirework(Entity s) {
        Firework fw = getFirework(s);
        FireworkMeta fwMeta = fw.getFireworkMeta();
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
