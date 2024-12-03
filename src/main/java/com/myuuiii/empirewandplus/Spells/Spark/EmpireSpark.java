package com.myuuiii.empirewandplus.Spells.Spark;

import com.myuuiii.empirewandplus.Abstracts.SparkSpellBase;
import com.myuuiii.empirewandplus.Data.SpellValues;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.myuuiii.empirewandplus.Extensions.getFirework;

public class EmpireSpark extends SparkSpellBase {

    private int _blindnessDuration = 100;

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (!(entity instanceof LivingEntity livingEntity))
            return;
        livingEntity.damage(getDamage());
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        loc = loc.add(0, 1, 0);
        loc.getWorld().spawnParticle(Particle.LARGE_SMOKE, loc, 100, 0.8, 0.8, 0.8, 0.01);
        loc.getWorld().spawnParticle(Particle.EFFECT, loc, 100, 0.8, 0.8, 0.8, 0.05);
    }

    @Override
    public void LaunchSpellFirework(Location loc, Player p) {
        Firework fw = getFirework(p, loc);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.setPower(0);

        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(150, 0, 150))
                .withFade(Color.fromRGB(228, 0, 120))
                .with(FireworkEffect.Type.BURST)
                .build());

        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }

    @Override
    public int getMaxReach() {
        return SpellValues.SPARK_REACH;
    }

    @Override
    public double getInRangeDistance() {
        return 3.0;
    }

    @Override
    public double getDamage() {
        return 8.0;
    }
}
