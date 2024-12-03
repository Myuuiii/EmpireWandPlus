package com.myuuiii.empirewandplus.Spells.Leap;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Leap extends Spell {

    private int velocityMultiplication = 6;
    private int cloudParticleCount = 100;

    @Override
    public int getMaxReach() {
        return 10;
    }

    @Override
    public double getInRangeDistance() {
        return 10;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {

    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);
        p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), cloudParticleCount, 0, 0, 0, 0.1);

        p.setVelocity(p.getLocation().getDirection().multiply(velocityMultiplication));

        if (!SpellEntityLists.LEAP_PLAYERS.contains(p.getUniqueId())) {
            SpellEntityLists.LEAP_PLAYERS.add(p.getUniqueId());
        }
    }
}
