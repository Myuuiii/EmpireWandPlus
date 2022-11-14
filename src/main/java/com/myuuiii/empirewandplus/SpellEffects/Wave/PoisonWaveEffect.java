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

public class PoisonWaveEffect {
    //
    // Settings
    //
    private static int _poisonDuration = 100;

    public static void Execute(Entity e) {

        Spell spell = EmpireWandPlus.spellHashMap.get(SpellNames.PoisonWave);

        new BukkitRunnable() {
            public void run() {
                if (SpellEntityLists.POISON_WAVE_ENTITIES.contains(e)) {
                    if (e.isDead()) {
                        // Executed when the entity is destroyed
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    launchFirework(e);
                    e.getWorld().playSound(e.getLocation(), Sound.BLOCK_AZALEA_BREAK, 1, 0.65f);

                    final List<Entity> near = getNearbyEntities(spell.getCloseRange(), e);
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
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);
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
