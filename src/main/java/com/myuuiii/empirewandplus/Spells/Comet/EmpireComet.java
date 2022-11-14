package com.myuuiii.empirewandplus.Spells.Comet;

import com.myuuiii.empirewandplus.Abstracts.RangedSpell;
import com.myuuiii.empirewandplus.SpellEntityLists;
import com.myuuiii.empirewandplus.SpellValues;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class EmpireComet extends RangedSpell {
    @Override
    public int getRangeMultiplication() {
        return 3;
    }

    @Override
    public void AddProjectileToList(Entity e) {
        SpellEntityLists.EMPIRE_COMET_ENTITIES.add(e);
    }

    @Override
    public void RemoveProjectileFromList(Entity e) {
        SpellEntityLists.EMPIRE_COMET_ENTITIES.remove(e);
    }

    @Override
    public void AddPlayerToList(Player p) {

    }

    @Override
    public void RemovePlayerFromList(Player p) {

    }

    @Override
    public int getReach() {
        return SpellValues.RANGED_REACH;
    }

    @Override
    public double getCloseRange() {
        return 10;
    }

    @Override
    public double getDamage() {
        return 8;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {

    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {

    }
}
