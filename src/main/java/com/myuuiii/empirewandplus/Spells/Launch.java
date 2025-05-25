package com.myuuiii.empirewandplus.Spells;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Launch extends Spell {

    private static final double _launchHeightModifier = 1.5;

    @Override
    public int getMaxReach() {
        return 10;
    }

    @Override
    public double getInRangeDistance() {
        return 3;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public String getConfigName() {
        return "launch";
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        entity.setVelocity(new Vector(entity.getVelocity().getX(), _launchHeightModifier, entity.getVelocity().getZ()));
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().spawnParticle(Particle.CLOUD, loc, 250, 1.5, 0, 1.5, 0.1);
    }
}
