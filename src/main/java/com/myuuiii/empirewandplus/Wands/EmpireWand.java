package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellNames;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.myuuiii.empirewandplus.Wands.WandMethods.CycleSpell;
import static com.myuuiii.empirewandplus.Wands.WandMethods.ExecuteSpellOnLeftClick;

public class EmpireWand extends Wand {

    public List<String> Spells = new ArrayList<>() {{
        add(SpellNames.Spark);
        add(SpellNames.EmpireSpark);
        add(SpellNames.BloodSpark);
        add(SpellNames.PoisonSpark);

        add(SpellNames.BloodWave);
        add(SpellNames.PoisonWave);
        add(SpellNames.FlameWave);

        add(SpellNames.EmpireConfuse);
        add(SpellNames.CelestialConfuse);

        add(SpellNames.EmpireStun);
        add(SpellNames.CelestialStun);

        add(SpellNames.Capture);

        add(SpellNames.EmpireComet);
        add(SpellNames.FireComet);

        add(SpellNames.FirePulse);

        add(SpellNames.Fireball);

        add(SpellNames.Ignite);

        add(SpellNames.Launch);

        add(SpellNames.Leap);

        add(SpellNames.Lightning);

        add(SpellNames.Smite);
    }};
    private String permissionBase = EmpireWandPlus.PermissionPrefix + "empirewand.";

    @Override
    public String getDisplayName() {
        return ChatColor.GOLD + "Empire Wand";
    }

    @Override
    public ItemStack getItem() {
        ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
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
        final EmpireWand empireWand = (EmpireWand) EmpireWandPlus.wandHashMap.get("empire");
        //
        //
        //
        // Empire wand
        //
        //
        //
        p.getInventory().getItemInMainHand();
        if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory()
                .getItemInMainHand().getItemMeta().getDisplayName().equals(empireWand.getDisplayName())) {
            if (!p.hasPermission(empireWand.getUsePermissionName())) {
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
                p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 250, 0.5, 0.0, 0.5, 0.05);

                //
                // Spell cycling
                //
                CycleSpell(p, wand, meta, empireWand.Spells, empireWand);
                return;
            }

            //
            // LEFT CLICK HANDLING
            //
            ExecuteSpellOnLeftClick(e, p, wand);
        }
    }


}
