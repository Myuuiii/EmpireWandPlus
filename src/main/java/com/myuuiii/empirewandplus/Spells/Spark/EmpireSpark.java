package com.myuuiii.empirewandplus.Spells.Spark;

import com.myuuiii.empirewandplus.Abstracts.FireworksSpell;
import com.myuuiii.empirewandplus.Data.SpellValues;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.myuuiii.empirewandplus.Extensions.getFirework;

public class EmpireSpark extends FireworksSpell {

    private int _blindnessDuration = 100;

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.damage(getDamage());
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {

    }

    @Override
    public void LaunchSpellFirework(Location loc, Player p) {
        Firework fw = getFirework(p, loc);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.setPower(0);
        fwMeta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(150, 0, 150)).with(FireworkEffect.Type.BURST).withFlicker().build());
        fwMeta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(0, 0, 0)).with(FireworkEffect.Type.BURST).withTrail().build());
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
        return 8.0;
    }
}
