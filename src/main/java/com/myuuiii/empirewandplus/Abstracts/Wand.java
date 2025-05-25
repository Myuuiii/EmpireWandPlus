package com.myuuiii.empirewandplus.Abstracts;

import com.myuuiii.empirewandplus.Managers.MessagesManager;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Wands.WandMethods;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public abstract class Wand {
    protected List<String> spells = new ArrayList<>();
    
    // Common spell key for persistent data
    public static final String SPELL_KEY = "current_spell";

    // Core identity methods
    public abstract String getIdentifier();
    public abstract String getDisplayName();
    public abstract String getPrefix();
    protected abstract Material getWandMaterial();
    
    // Standardized method to get the config path
    protected String getConfigPath() {
        return "wands." + getIdentifier() + ".spells";
    }
    
    // Common implementation for loading spells from config
    public void loadSpellsFromConfig() {
        spells.clear();
        FileConfiguration config = EmpireWandPlus._plugin.getConfig();
        List<String> configSpells = config.getStringList(getConfigPath());
        if (configSpells != null && !configSpells.isEmpty()) {
            spells.addAll(configSpells);
        } else {
            addDefaultSpells();
        }
    }
    
    // Abstract method that subclasses implement to provide default spells
    protected abstract void addDefaultSpells();
    
    // Return the spells for this wand type
    public List<String> getSpellList() {
        return spells;
    }
    
    // Common implementation for creating the wand item
    public ItemStack getItem() {
        ItemStack wand = new ItemStack(getWandMaterial(), 1);
        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.setDisplayName(getDisplayName());
        
        // Get the spell list for this wand type
        List<String> spellList = getSpellList();
        if (spellList == null || spellList.isEmpty()) {
            return wand; // Return basic wand if no spells available
        }
        
        // Store the spell name in persistent data
        PersistentDataContainer container = wandMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(EmpireWandPlus._plugin, SPELL_KEY);
        container.set(key, PersistentDataType.STRING, spellList.get(0));
        
        wand.setItemMeta(wandMeta);
        return wand;
    }

    // Permission Names
    public String getPermissionBase() {
        return EmpireWandPlus.PermissionPrefix + getIdentifier().toLowerCase() + ".";
    }

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

            List<String> spellList = getSpellList();
            if (spellList == null || spellList.isEmpty()) {
                p.sendMessage(MessagesManager.getErrorMessage("no-spell-set"));
                return;
            }

            if (!p.hasPermission(wand.getSwitchSpellPermissionName())) {
                p.sendMessage(MessagesManager.getErrorMessage("no-switch-permission"));
                return;
            }
        
            WandMethods.CycleSpell(p, wandItemStack, wandMeta, spellList, wand);
            return;
        }

        WandMethods.ExecuteSpellOnLeftClick(e, p, wandItemStack);
    }

    // Standard implementation that fetches the wand from wandHashMap
    public void Handle(final PlayerInteractEvent e) {
        String wandType = getIdentifier().toLowerCase();
        Wand wand = EmpireWandPlus.wandHashMap.get(wandType);
        if (wand != null) {
            HandleInteraction(e, wand);
        }
    }

    public abstract void SwitchEffects(final PlayerInteractEvent e);
}