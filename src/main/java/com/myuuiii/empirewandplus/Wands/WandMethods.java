package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Extensions;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class WandMethods {

    public static void CycleSpell(Player p, ItemStack wandItem, ItemMeta meta, List<String> spells, Wand wand) {
        List<String> loreItems = new ArrayList<>();

        if (wandItem.getItemMeta().hasLore()) {
            loreItems = wandItem.getItemMeta().getLore();

            assert loreItems != null;
            loreItems.set(0, Extensions.GetNextSpell(spells, wandItem, p));
        } else {
            loreItems.add(spells.get(0));
        }

        meta.setLore(loreItems);
        wandItem.setItemMeta(meta);
        p.sendMessage(wand.getPrefix() + wandItem.getItemMeta().getLore().get(0));
    }

    public static void ExecuteSpellOnLeftClick(PlayerInteractEvent e, Player p, ItemStack wand) {
        p.sendMessage("[DEBUG] Event triggered: PlayerInteractEvent");
        p.sendMessage("[DEBUG] Action detected: " + e.getAction());

        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            p.sendMessage("[DEBUG] Left-click action detected. Proceeding...");
            e.setCancelled(true);

            // Wand validation
            p.sendMessage("[DEBUG] Validating wand...");
            if (wand == null) {
                p.sendMessage("[DEBUG] Wand is null! Aborting execution.");
                return;
            }
            p.sendMessage("[DEBUG] Wand is not null.");

            // Metadata validation
            ItemMeta meta = wand.getItemMeta();
            p.sendMessage("[DEBUG] Checking wand metadata...");
            if (meta == null) {
                p.sendMessage("[DEBUG] Metadata is null! Aborting execution.");
                return;
            }
            p.sendMessage("[DEBUG] Metadata is valid.");

            // Lore validation
            List<String> lore = meta.getLore();
            p.sendMessage("[DEBUG] Checking wand lore...");
            if (lore == null) {
                p.sendMessage("[DEBUG] Lore is null! Aborting execution.");
                return;
            }
            p.sendMessage("[DEBUG] Lore is present. Lore content: " + lore);

            // Retrieve the current spell
            String spellName = lore.get(0);
            p.sendMessage("[DEBUG] Current spell retrieved from lore: " + spellName);

            Spell spell = EmpireWandPlus.spellHashMap.get(spellName);
            p.sendMessage("[DEBUG] Spell lookup in hash map...");
            if (spell == null) {
                p.sendMessage("[DEBUG] Spell not found in hash map! Spell name: " + spellName);
                return;
            }
            p.sendMessage("[DEBUG] Spell found. Spell object: " + spell);
            p.sendMessage("[DEBUG] Spell reach: " + spell.getReach());

            // Validate reach
            int reach = spell.getReach();
            if (reach <= 0) {
                p.sendMessage("[DEBUG] Invalid reach value: " + reach + ". Aborting execution.");
                return;
            }

            // Target block retrieval
            p.sendMessage("[DEBUG] Attempting to get target block...");
            Block targetBlock = p.getTargetBlock(null, reach);

            if (targetBlock == null || targetBlock.getType() == Material.AIR || targetBlock.getType() == Material.LIGHT) {
                p.sendMessage("[DEBUG] Target block is invalid, AIR, or LIGHT. Initiating failsafe...");
                Location failsafeLocation = findSafeLocationAroundPlayerWithDebug(p, 10);
                if (failsafeLocation == null) {
                    p.sendMessage("[DEBUG] Failsafe could not determine a valid location. Aborting.");
                    return;
                }
                p.sendMessage("[DEBUG] Failsafe-determined location: " + failsafeLocation);
                spell.Execute(failsafeLocation, p);
            } else {
                Location targetLocation = targetBlock.getLocation();
                p.sendMessage("[DEBUG] Valid target block found. Location: " + targetLocation);
                spell.Execute(targetLocation, p);
            }
        } else {
            p.sendMessage("[DEBUG] Action is not left-click. Event ignored.");
        }
    }

    /**
     * Finds a safe location around the player with detailed debug messages.
     *
     * @param player The player whose surroundings to check.
     * @param distance The distance to check in each direction.
     * @return A safe location or null if none is found.
     */
    private static Location findSafeLocationAroundPlayerWithDebug(Player player, int distance) {
        Location playerLocation = player.getLocation();
        player.sendMessage("[DEBUG] Player's current location: " + playerLocation);

        Vector[] directions = {
                player.getLocation().getDirection().normalize(), // Forward
                player.getLocation().getDirection().multiply(-1).normalize(), // Backward
                new Vector(1, 0, 0), // Right
                new Vector(-1, 0, 0) // Left
        };

        String[] directionNames = {"Forward", "Backward", "Right", "Left"};

        for (int i = 0; i < directions.length; i++) {
            Vector direction = directions[i];
            String directionName = directionNames[i];

            player.sendMessage("[DEBUG] Checking direction: " + directionName);
            player.sendMessage("[DEBUG] Direction vector: " + direction);

            Location potentialLocation = playerLocation.clone().add(direction.multiply(distance));
            Material blockType = potentialLocation.getBlock().getType();

            player.sendMessage("[DEBUG] Checking location: " + potentialLocation);
            player.sendMessage("[DEBUG] Block type at location: " + blockType);

            if (blockType == Material.AIR) {
                player.sendMessage("[DEBUG] Safe location found in direction: " + directionName);
                return potentialLocation;
            } else {
                player.sendMessage("[DEBUG] Location is not safe. Block type: " + blockType);
            }
        }

        player.sendMessage("[DEBUG] No valid safe location found in any direction.");
        return null; // No valid location found
    }
}