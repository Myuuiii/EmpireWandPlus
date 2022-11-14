package com.myuuiii.empirewandplus.Spells.Spark;

import com.myuuiii.empirewandplus.Abstracts.FireworksSpell;
import com.myuuiii.empirewandplus.SpellValues;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.myuuiii.empirewandplus.Extensions.getFirework;

public class BloodSpark extends FireworksSpell {

    private static int _witherDuration = 100;

    @Override
    public void LaunchSpellFirework(Location loc, Player p) {
        Firework fw = getFirework(p, loc);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(255, 0, 0)).withFade(Color.fromRGB(150, 0, 0)).with(FireworkEffect.Type.BURST).withFlicker().build());
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
        return 6.0;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.damage(getDamage());
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, _witherDuration, 1, true, false));
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_BREAK, 5, 0.65f);
        p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_LEAVES_BREAK, 5, 0.65f);
    }
}
