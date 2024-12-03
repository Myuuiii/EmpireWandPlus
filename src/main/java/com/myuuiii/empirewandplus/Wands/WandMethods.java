package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Extensions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            e.setCancelled(true);

            // Retrieve the spell that is to be executed
            Spell spell = EmpireWandPlus.spellHashMap.get(wand.getItemMeta().getLore().get(0));

            // Get the target location
            final Location loc = p.getTargetBlock(null, spell.getMaxReach()).getLocation();

            // Execute the spell given the targeted location and player
            spell.Execute(loc, p);
        }
    }
}
