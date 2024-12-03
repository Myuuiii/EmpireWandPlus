package com.myuuiii.empirewandplus.Abstracts;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public abstract class SparkSpellBase extends Spell {
    public abstract void LaunchSpellFirework(Location loc, Player p);

    @Override
    public void Execute(Location loc, Player p) {
        loc.add(0, 1, 0);

        LaunchSpellFirework(loc, p);

        atExecutingLocation(loc, p);

        final List<Entity> nearbyEntities = getNearbyEntities(getCloseRange(), loc);
        for (Entity e : nearbyEntities) {
            forAllNearbyEntities(e, loc, p);
        }
    }
}
