package com.myuuiii.empirewandplus.Abstracts;

import com.myuuiii.empirewandplus.Managers.MessagesManager;
import com.myuuiii.empirewandplus.Wands.BloodWand;
import com.myuuiii.empirewandplus.Wands.ElementosWand;
import com.myuuiii.empirewandplus.Wands.EmpireWand;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.myuuiii.empirewandplus.Wands.WandMethods.CycleSpell;
import static com.myuuiii.empirewandplus.Wands.WandMethods.ExecuteSpellOnLeftClick;

public abstract class Wand {
    public List<String> Spells;
    public String Identifier = "";

    public abstract String getDisplayName();

    public abstract String getPrefix();

    public abstract ItemStack getItem();

    // Permission Names
    public abstract String getPermissionBase();

    public String getUsePermissionName() {
        return getPermissionBase() + "use";
    }

    public String getSwitchSpellPermissionName() {
        return getPermissionBase() + "switch";
    }
    
    public String getObtainPermissionName() {
        return getPermissionBase() + "obtain";
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
            p.sendMessage(MessagesManager.getErrorMessage("no-permission"));
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
            p.sendMessage(MessagesManager.getErrorMessage("no-spell-set"));
                return;
            }

            if (!p.hasPermission(wand.getSwitchSpellPermissionName())) {
                p.sendMessage(MessagesManager.getErrorMessage("no-switch-permission"));
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