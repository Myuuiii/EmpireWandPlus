package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Extensions;
import com.myuuiii.empirewandplus.Managers.SpellNameManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class WandMethods {

    public static void CycleSpell(Player p, ItemStack wandItem, ItemMeta meta, List<String> spells, Wand wand) {
        String nextSpell;
        
        // Get the current spell name from meta
        PersistentDataContainer container = wandItem.getItemMeta().getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(EmpireWandPlus._plugin, Wand.SPELL_KEY);
        
        String currentSpell = null;
        if (container.has(key, PersistentDataType.STRING)) {
            currentSpell = container.get(key, PersistentDataType.STRING);
        }
        
        // Get the next spell, passing the player to check for sneaking
        nextSpell = getNextSpell(spells, currentSpell, p);
    
        // Store the actual spell name in the item's metadata
        container = meta.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, nextSpell);
        
        // Update item meta
        wandItem.setItemMeta(meta);
        
        // Display the spell's friendly name to the player
        String displayName = SpellNameManager.getDisplayName(nextSpell);
        p.sendMessage(wand.getPrefix() + displayName);
    }
    
    private static String getNextSpell(List<String> spells, String currentSpell, Player player) {
        if (currentSpell == null || spells.isEmpty()) {
            return spells.isEmpty() ? null : spells.get(0);
        }
        
        int currentIndex = spells.indexOf(currentSpell);
        if (currentIndex == -1) {
            return spells.get(0);
        }
        
        int modifier = player.isSneaking() ? -1 : 1;
        int nextIndex = (currentIndex + modifier) % spells.size();
        
        // Handle negative index when going backwards from first spell
        if (nextIndex < 0) {
            nextIndex = spells.size() - 1;
        }
        
        return spells.get(nextIndex);
    }

    public static void ExecuteSpellOnLeftClick(PlayerInteractEvent e, Player p, ItemStack wand) {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            e.setCancelled(true);
            
            // Get the spell from the wand's persistent data
            ItemMeta meta = wand.getItemMeta();
            if (meta == null) return;
            
            PersistentDataContainer container = meta.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(EmpireWandPlus._plugin, Wand.SPELL_KEY);
            
            if (!container.has(key, PersistentDataType.STRING)) {
                // No spell found, notify player and return
                p.sendMessage(EmpireWandPlus.Prefix + "Error: No spell selected");
                return;
            }
            
            String spellName = container.get(key, PersistentDataType.STRING);
            
            // Retrieve the spell that is to be executed
            Spell spell = EmpireWandPlus.spellHashMap.get(spellName);
            if (spell == null) {
                p.sendMessage(EmpireWandPlus.Prefix + "Error: Invalid spell '" + spellName + "'");
                return;
            }

            // Get the target location
            final Location loc = p.getTargetBlock(null, spell.getMaxReach()).getLocation();

            // Execute the spell given the targeted location and player
            spell.Execute(loc, p);
        }
    }
}
