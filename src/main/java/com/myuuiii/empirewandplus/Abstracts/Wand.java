package com.myuuiii.empirewandplus.Abstracts;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.myuuiii.empirewandplus.Wands.WandMethods.CycleSpell;
import static com.myuuiii.empirewandplus.Wands.WandMethods.ExecuteSpellOnLeftClick;

import com.myuuiii.empirewandplus.Wands.BloodWand;
import com.myuuiii.empirewandplus.Wands.ElementosWand;
import com.myuuiii.empirewandplus.Wands.EmpireWand;

import java.util.ArrayList;
import java.util.List;

public abstract class Wand {
    public List<String> Spells;
    public String permissionBase = "";

    public abstract String getDisplayName();

    public abstract String getPrefix();

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

    public boolean checkWandHeldState(final PlayerInteractEvent playerInteractionEvent, final Wand wand) {
        final Player p = playerInteractionEvent.getPlayer();
        return p.getInventory().getItemInMainHand().hasItemMeta()
                && p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory()
                        .getItemInMainHand().getItemMeta().getDisplayName().equals(wand.getDisplayName());
    }

    public boolean IsRightClickInteraction(final PlayerInteractEvent playerInteractionEvent) {
        var state = playerInteractionEvent.getAction() == Action.RIGHT_CLICK_AIR || playerInteractionEvent.getAction() == Action.RIGHT_CLICK_BLOCK;
        if (state)
            playerInteractionEvent.setCancelled(true);
        return state;
    }

    public void HandleInteraction(PlayerInteractEvent e, final Wand wand) {
        final Player p = e.getPlayer();
        p.getInventory().getItemInMainHand();

        if (!checkWandHeldState(e, wand))
            return;

        if (!p.hasPermission(wand.getUsePermissionName())) {
            p.sendMessage(ChatColor.RED + "You're not allowed to use that!");
            return;
        }

        final ItemStack wandItemStack = p.getInventory().getItemInMainHand();
        final ItemMeta wandMeta = wandItemStack.getItemMeta();

        if (IsRightClickInteraction(e)) {

            SwitchEffects(e);

            List<String> spells = null;
            if (wand instanceof EmpireWand) spells = EmpireWand.Spells;
            else if (wand instanceof BloodWand) spells = BloodWand.Spells;
            else if (wand instanceof ElementosWand) spells = ElementosWand.Spells;
            else {
                p.sendMessage(ChatColor.RED + "No spell set found for this wand!");
                return;
            }

            CycleSpell(p, wandItemStack, wandMeta, spells, wand);

            return;
        }

        ExecuteSpellOnLeftClick(e, p, wandItemStack);
    }

    public abstract void Handle(final PlayerInteractEvent e);

    public abstract void SwitchEffects(final PlayerInteractEvent e);
}
