package com.myuuiii.empirewandplus.Spells.Spark;

import com.myuuiii.empirewandplus.Abstracts.SparkSpellBase;
import com.myuuiii.empirewandplus.Data.SpellValues;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.myuuiii.empirewandplus.Extensions.getFirework;

public class PoisonSpark extends SparkSpellBase {

    private static int _poisonDuration = 150;

    @Override
    public void LaunchSpellFirework(Location loc, Player p) {
        Firework fw = getFirework(p, loc);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.setPower(0);
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(0, 255, 0))
                .withFade(Color.fromRGB(0, 2, 0))
                .with(FireworkEffect.Type.BURST)
                .withFlicker()
                .build()
        );
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(0, 0, 0))
                .withFade(Color.fromRGB(0, 0, 0))
                .with(FireworkEffect.Type.BURST)
                .build()
        );
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }

    @Override
    public int getReach() {
        return SpellValues.SPARK_REACH;
    }

    @Override
    public double getCloseRange() {
        return 3.0;
    }

    @Override
    public double getDamage() {
        return 4.0;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.damage(getDamage());
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, _poisonDuration, 1, true, false));
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().playSound(loc, Sound.ENTITY_ZOMBIE_INFECT, 5, 0.85f);
        p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_LEAVES_BREAK, 5, 0.85f);
        p.getWorld().spawnParticle(Particle.DUST, loc.add(0, 1, 0), 100, 0.7, 0.7, 0.7, 1, new Particle.DustOptions(Color.fromRGB(0, 255, 0), 1));
        p.getWorld().spawnParticle(Particle.SMOKE, loc.add(0, 1, 0), 150, 0.7, 0.7, 0.7, 0.1);
    }
}
