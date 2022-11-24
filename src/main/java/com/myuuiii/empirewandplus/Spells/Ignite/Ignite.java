package com.myuuiii.empirewandplus.Spells.Ignite;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Ignite extends Spell {

    private static int _fireDuration = 150;

    @Override
    public int getReach() {
        return 10;
    }

    @Override
    public double getCloseRange() {
        return 4;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.setFireTicks(_fireDuration);
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 100, 1, 1, 1, 0.1);
        p.getWorld().spawnParticle(Particle.FLAME, loc, 75, 1, 1, 1, 0.3);
        p.getWorld().spawnParticle(Particle.LAVA, loc, 75, 1, 1, 1, 1);

        p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_SHOOT, 5, 0.85f);
        p.getWorld().playSound(loc, Sound.BLOCK_FURNACE_FIRE_CRACKLE, 5, 0.85f);
    }
}
