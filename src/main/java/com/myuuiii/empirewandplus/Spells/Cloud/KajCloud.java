package com.myuuiii.empirewandplus.Spells.Cloud;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Data.SpellEntityLists;
import com.myuuiii.empirewandplus.Data.SpellNames;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class KajCloud extends Spell {

    @Override
    public int getReach() {
        return 0;
    }

    @Override
    public double getCloseRange() {
        return 0;
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
        UUID uuid = p.getUniqueId();
        if (SpellEntityLists.KAJ_CLOUD_PLAYERS.contains(uuid)) {
            if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                p.setAllowFlight(false);
            }
            SpellEntityLists.KAJ_CLOUD_PLAYERS.remove(p.getUniqueId());
            p.sendMessage(SpellNames.KajCloud + " disabled");
        } else {
            if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                p.setAllowFlight(true);
            }
            SpellEntityLists.KAJ_CLOUD_PLAYERS.add(p.getUniqueId());
            p.sendMessage(SpellNames.KajCloud + " enabled");
        }
    }
}
