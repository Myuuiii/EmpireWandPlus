package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Data.SpellNames;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.myuuiii.empirewandplus.Extensions.colorText;

import java.util.ArrayList;
import java.util.List;


public class ElementosWand extends Wand {

    public static List<String> Spells = new ArrayList<>() {
        {
            add(SpellNames.Spark);
            add(SpellNames.CelestialConfuse);
            add(SpellNames.CelestialStun);
            add(SpellNames.Lightning);
            add(SpellNames.Smite);
            add(SpellNames.KajCloud);
        }
    };

    public final String permissionBase = EmpireWandPlus.PermissionPrefix + "elementoswand.";

    @Override
    public String getDisplayName() {
        return ChatColor.AQUA + "Elementos Wand";
    }

    
    @Override
    public String getPrefix() {
        return colorText("&8[&3Elementos Wand&8]&r ");
    }

    @Override
    public ItemStack getItem() {
        ItemStack wand = new ItemStack(Material.ECHO_SHARD, 1);
        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.setDisplayName(getDisplayName());
        wandMeta.setLore(new ArrayList<>() {
            {
                add(Spells.get(0));
            }
        });
        wand.setItemMeta(wandMeta);
        return wand;
    }

    @Override
    public void Handle(PlayerInteractEvent e) {
        final ElementosWand elementosWand = (ElementosWand) EmpireWandPlus.wandHashMap.get("elementos");
        HandleInteraction(e, elementosWand);
    }

    @Override
    public void SwitchEffects(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 10.0f, 1.0f);
        p.getWorld().spawnParticle(Particle.ENCHANT, p.getLocation(), 50, 0.4, 0.5, 0.4, 0.0);
        p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, p.getLocation().add(0, 0.3, 0), 50, 0.4, 0.5, 0.4, 0);
        p.getWorld().spawnParticle(Particle.FIREWORK, p.getLocation().add(0, 0.3, 0), 50, 0.4, 0.5, 0.4, 0);
    }
}
