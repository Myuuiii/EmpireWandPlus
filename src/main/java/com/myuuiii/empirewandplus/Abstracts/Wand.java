package com.myuuiii.empirewandplus.Abstracts;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Wand {
    public List<String> Spells;
    private String permissionBase = "";

    public abstract String getDisplayName();

    public abstract ItemStack getItem();

    // Permission Names
    public String getObtainPermissionName() {
        return permissionBase + "obtain";
    }

    public String getUsePermissionName() {
        return permissionBase + "use";
    }

    public String getSwitchSpellPermissionName() {
        return permissionBase + "switch";
    }

    public abstract void Handle(final PlayerInteractEvent e);
    public abstract void SwitchEffects(final PlayerInteractEvent e);
}

