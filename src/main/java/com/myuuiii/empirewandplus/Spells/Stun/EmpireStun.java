package com.myuuiii.empirewandplus.Spells.Stun;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Data.SpellValues;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EmpireStun extends Spell {

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
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, _slowDuration, 255, true, false));
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc, 100, 0.5, 1, 0.5, 0.1);
        p.getWorld().spawnParticle(Particle.WITCH, loc, 100, 0.5, 1, 0.5, 0.1);
        p.getWorld().spawnParticle(Particle.DUST, loc, 75, 0.5, 1, 0.5, 3, new Particle.DustOptions(Color.fromRGB(255, 0, 233), 2));

        p.getWorld().playSound(loc, Sound.ENTITY_ENDERMAN_SCREAM, 5, 0.65f);
        p.getWorld().playSound(loc, Sound.ITEM_TOTEM_USE, 5, 0.65f);
    }
}
