package com.myuuiii.empirewandplus;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public class Extensions {
    public static boolean CheckIfInRange(int range, Location loc, Player player) {
        return loc.distance(player.getLocation()) <= range;
    }

    public static String GetNextSpell(List<String> spellList, ItemStack wand, Player player) {
        int currentSpellIndex = spellList.indexOf(wand.getItemMeta().getLore().get(0));

        int modifier;

        if (player.isSneaking())
            modifier = -1;
        else
            modifier = 1;

        int nextSpellIndex = currentSpellIndex + modifier;

        if (nextSpellIndex >= spellList.size())
            nextSpellIndex = 0;
        else if (nextSpellIndex < 0)
            nextSpellIndex = spellList.size() - 1;

        return spellList.get(nextSpellIndex);
    }

    public static String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<Entity> getNearbyEntities(double _closeRange, Entity e) {
        return (List<Entity>) e.getWorld().getNearbyEntities(e.getLocation(),
                _closeRange, _closeRange, _closeRange);
    }

    public static List<Entity> getNearbyEntities(double _closeRange, Location loc) {
        return (List<Entity>) loc.getWorld().getNearbyEntities(loc, _closeRange, _closeRange,
                _closeRange);
    }

    public static Firework getFirework(Entity entity) {
        Firework fw = entity.getWorld().spawn(entity.getLocation(), Firework.class);
        fw.setMetadata("nodamage", new FixedMetadataValue(EmpireWandPlus._plugin, true));
        return fw;
    }

    public static Firework getFirework(Entity entity, Location loc) {
        Firework fw = entity.getWorld().spawn(loc, Firework.class);
        fw.setMetadata("nodamage", new FixedMetadataValue(EmpireWandPlus._plugin, true));
        return fw;
    }
}
