package com.myuuiii.empirewandplus.Spells.Spark;

import com.myuuiii.empirewandplus.Abstracts.SparkSpellBase;
import com.myuuiii.empirewandplus.Data.SpellValues;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import static com.myuuiii.empirewandplus.Extensions.getFirework;

public class Spark extends SparkSpellBase {
    @Override
    public void LaunchSpellFirework(Location loc, Player p) {
        Firework fw = getFirework(p, loc);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.setPower(0);
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(255, 255, 255))
                .withFade(Color.fromRGB(255, 0, 0))
                .with(FireworkEffect.Type.BURST)
                .build()
        );
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }

    @Override
    public int getMaxReach() {
        return SpellValues.SPARK_REACH;
    }

    @Override
    public double getInRangeDistance() {
        return 3;
    }

    @Override
    public double getDamage() {
        return 3;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.damage(getDamage());
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {

    }
}
