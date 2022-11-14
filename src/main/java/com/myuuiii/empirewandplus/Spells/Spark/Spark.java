package com.myuuiii.empirewandplus.Spells.Spark;

import com.myuuiii.empirewandplus.Abstracts.FireworksSpell;
import com.myuuiii.empirewandplus.SpellValues;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import static com.myuuiii.empirewandplus.Extensions.getFirework;

public class Spark extends FireworksSpell {
    @Override
    public void LaunchSpellFirework(Location loc, Player p) {
        Firework fw = getFirework(p, loc);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(255, 255, 255))
                .with(FireworkEffect.Type.BURST)
                .build()
        );
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(255, 0, 0))
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
