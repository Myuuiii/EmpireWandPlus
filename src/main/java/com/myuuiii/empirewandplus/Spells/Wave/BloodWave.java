package com.myuuiii.empirewandplus.Spells.Wave;

import com.myuuiii.empirewandplus.Abstracts.ProjectileSpell;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import com.myuuiii.empirewandplus.Data.SpellValues;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BloodWave extends ProjectileSpell {

    private static int _witherDuration = 100;

    @Override
    public int getRangeMultiplication() {
        return 3;
    }

    @Override
    public void AddProjectileToList(Entity e) {
        SpellEntityLists.BLOOD_WAVE_ENTITIES.add(e);
    }

    @Override
    public void RemoveProjectileFromList(Entity e) {
        SpellEntityLists.BLOOD_WAVE_ENTITIES.remove(e);
    }

    @Override
    public void AddPlayerToList(Player p) {
        SpellEntityLists.BLOOD_WAVE_PLAYERS.add(p.getUniqueId());
    }

    @Override
    public void RemovePlayerFromList(Player p) {
        SpellEntityLists.BLOOD_WAVE_PLAYERS.remove(p.getUniqueId());
    }

    @Override
    public int getMaxReach() {
        return SpellValues.RANGED_REACH;
    }

    @Override
    public double getInRangeDistance() {
        return 3;
    }

    @Override
    public double getDamage() {
        return 2;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {

    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {

    }
}
