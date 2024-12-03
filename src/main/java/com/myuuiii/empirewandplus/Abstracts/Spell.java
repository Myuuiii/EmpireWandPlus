package com.myuuiii.empirewandplus.Abstracts;

import com.myuuiii.empirewandplus.Interfaces.ISpell;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

import static com.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public abstract class Spell implements ISpell {

    public void Execute(Location loc, Player p) {
        loc.add(0, 1, 0);

        atExecutingLocation(loc, p);

        final List<Entity> nearbyEntities = getNearbyEntities(getInRangeDistance(), loc);
        for (Entity e : nearbyEntities) {
            forAllNearbyEntities(e, loc, p);
        }
    }

    public abstract int getMaxReach();

    public abstract double getInRangeDistance();

    public abstract double getDamage();
}
