package com.myuuiii.empirewandplus.Spells.Stun;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.SpellValues;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CelestialStun extends Spell {

    public static int _blindnessDuration = 100;
    private static int _slowDuration = 100;

    @Override
    public int getReach() {
        return SpellValues.STUN_REACH;
    }

    @Override
    public double getCloseRange() {
        return 3;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, _slowDuration, 255, true, false));
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 100, 0.5, 1, 0.5, 0.5);
        p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, loc, 100, 0.5, 1, 0.5, 0.1);
        p.getWorld().spawnParticle(Particle.SNOWFLAKE, loc.add(0, 2, 0), 100, 0.5, 1, 0.5, 0);

        p.getWorld().playSound(loc, Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 2, 0.65f);
        p.getWorld().playSound(loc, Sound.ITEM_TOTEM_USE, 2, 0.65f);
    }
}
