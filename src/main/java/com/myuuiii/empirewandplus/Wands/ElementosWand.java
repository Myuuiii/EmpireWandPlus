package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Data.SpellNames;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Wands.WandMethods.CycleSpell;
import static com.myuuiii.empirewandplus.Wands.WandMethods.ExecuteSpellOnLeftClick;

public class ElementosWand extends Wand {

    public List<String> Spells = new ArrayList<>() {{
        add(SpellNames.Spark);
        add(SpellNames.CelestialConfuse);
        add(SpellNames.CelestialStun);
        add(SpellNames.Lightning);
        add(SpellNames.Smite);
        add(SpellNames.KajCloud);
    }};

    private final String permissionBase = EmpireWandPlus.PermissionPrefix + "elementoswand.";

    @Override
    public String getDisplayName() {
        return ChatColor.AQUA + "Elementos Wand";
    }

    @Override
    public ItemStack getItem() {
        ItemStack wand = new ItemStack(Material.ECHO_SHARD, 1);
        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.setDisplayName(getDisplayName());
        wandMeta.setLore(new ArrayList<>() {{
            add(Spells.get(0));
        }});
        wand.setItemMeta(wandMeta);
        return wand;
    }

    @Override
    public void Handle(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ElementosWand elementosWand = (ElementosWand) EmpireWandPlus.wandHashMap.get("elementos");
        //
        //
        //
        // Elementos wand
        //
        //
        //
        p.getInventory().getItemInMainHand();
        if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory()
                .getItemInMainHand().getItemMeta().getDisplayName().equals(elementosWand.getDisplayName())) {
            if (!p.hasPermission(elementosWand.getUsePermissionName())) {
                p.sendMessage(ChatColor.RED + "You're not allowed to use that!");
                return;
            }

            final ItemStack wand = p.getInventory().getItemInMainHand();

            //
            // Right Click Handling
            //
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.setCancelled(true);

                final ItemMeta meta = wand.getItemMeta();
                
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 10.0f, 1.0f);
                p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 250, 0.5, 0.0, 0.5, 0.05);

                //
                // Spell cycling
                //
                CycleSpell(p, wand, meta, elementosWand.Spells, elementosWand);
                return;
            }

            //
            // LEFT CLICK HANDLING
            //
            ExecuteSpellOnLeftClick(e, p, wand);
        }
    }
}
