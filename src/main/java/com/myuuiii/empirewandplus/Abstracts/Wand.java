package com.myuuiii.empirewandplus.Abstracts;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
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

    public boolean checkWandHeldState(final PlayerInteractEvent e, final Wand wand) {
        final Player p = e.getPlayer();
        return p.getInventory().getItemInMainHand().hasItemMeta()
                && p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory()
                        .getItemInMainHand().getItemMeta().getDisplayName().equals(wand.getDisplayName());
    }

    public boolean IsRightClickInteraction(final PlayerInteractEvent e) {
        var state = e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK;
        if (state)
            e.setCancelled(true);
        return state;
    }

    public abstract void Handle(final PlayerInteractEvent e);

    public abstract void SwitchEffects(final PlayerInteractEvent e);
}
