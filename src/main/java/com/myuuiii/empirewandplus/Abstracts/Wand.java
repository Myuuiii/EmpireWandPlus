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

import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Managers.MessagesManager;
import com.myuuiii.empirewandplus.Wands.BloodWand;
import com.myuuiii.empirewandplus.Wands.ElementosWand;
import com.myuuiii.empirewandplus.Wands.EmpireWand;
import com.myuuiii.empirewandplus.Wands.WandMethods;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public abstract class Wand {
    public List<String> Spells;
    public String Identifier = "";

    // Common spell key for persistent data
    public static final String SPELL_KEY = "current_spell";

    public abstract String getDisplayName();

    public abstract String getPrefix();

    // Methods for item creation
    protected abstract Material getWandMaterial();
    protected abstract List<String> getSpellList();
    
    // Common implementation for creating the wand item
    public ItemStack getItem() {
        ItemStack wand = new ItemStack(getWandMaterial(), 1);
        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.setDisplayName(getDisplayName());
        
        // Get the spell list for this wand type
        List<String> spells = getSpellList();
        if (spells == null || spells.isEmpty()) {
            return wand; // Return basic wand if no spells available
        }
        
        // Store the spell name in persistent data
        PersistentDataContainer container = wandMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(EmpireWandPlus._plugin, SPELL_KEY);
        container.set(key, PersistentDataType.STRING, spells.get(0));
        
        wand.setItemMeta(wandMeta);
        return wand;
    }

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

            List<String> spells = getSpellList();
            if (spells == null || spells.isEmpty()) {
                p.sendMessage(MessagesManager.getErrorMessage("no-spell-set"));
                return;
            }

            if (!p.hasPermission(wand.getSwitchSpellPermissionName())) {
                p.sendMessage(MessagesManager.getErrorMessage("no-switch-permission"));
                return;
            }
        
            WandMethods.CycleSpell(p, wandItemStack, wandMeta, spells, wand);
            return;
        }

        WandMethods.ExecuteSpellOnLeftClick(e, p, wandItemStack);
    }

    public abstract void Handle(final PlayerInteractEvent e);

    public abstract void SwitchEffects(final PlayerInteractEvent e);
}