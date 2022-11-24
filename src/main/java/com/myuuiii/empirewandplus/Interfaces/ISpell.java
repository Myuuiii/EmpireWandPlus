package com.myuuiii.empirewandplus.Interfaces;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface ISpell {
    void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer);

    void atExecutingLocation(Location loc, Player p);

}
