package com.myuuiii.empirewandplus.Spells.Confuse;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Data.SpellValues;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EmpireConfuse extends Spell {

    private static int _confusionDuration = 250;
    private static int _blindnessDuration = 200;
    private static int _slownessDuration = 175;

    @Override
    public int getReach() {
        return SpellValues.CONFUSE_REACH;
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
        if (!(entity instanceof LivingEntity targetEntity)) return;
        targetEntity.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, _confusionDuration, 1, true, false));
        targetEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));
        targetEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, _slownessDuration, 1, true, false));
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, loc, 250, 0.5, 1.5, 0.5, 0.1);
        p.getWorld().spawnParticle(Particle.ENCHANT, loc, 250, 0.2, 1.5, 0.2, 0.1);

        p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_DEATH, 1, 0.85f);
        p.getWorld().playSound(loc, Sound.ENTITY_CREEPER_DEATH, 2, 0.85f);
    }
}
