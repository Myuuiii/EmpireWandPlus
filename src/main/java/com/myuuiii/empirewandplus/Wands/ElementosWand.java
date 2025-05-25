package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.Data.SpellNames;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Managers.MessagesManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;


public class ElementosWand extends Wand {

    public final static String Identifier = "Elementos";

    public static List<String> Spells = new ArrayList<>();

    public static void loadSpellsFromConfig() {
        Spells.clear();
        FileConfiguration config = com.myuuiii.empirewandplus.EmpireWandPlus._plugin.getConfig();
        List<String> configSpells = config.getStringList("wands.Elementos.spells");
        if (configSpells != null && !configSpells.isEmpty()) {
            Spells.addAll(configSpells);
        } else {
            Spells.add(SpellNames.Spark);
            Spells.add(SpellNames.CelestialConfuse);
            Spells.add(SpellNames.CelestialStun);
            Spells.add(SpellNames.Lightning);
            Spells.add(SpellNames.Smite);
            Spells.add(SpellNames.KajCloud);
        }
    }

    @Override
    public String getDisplayName() {
        return MessagesManager.getWandDisplayName("elementos");
    }


    @Override
    public String getPrefix() {
        return MessagesManager.getWandPrefix("elementos");
    }

    @Override
    public ItemStack getItem() {
        ItemStack wand = new ItemStack(Material.ECHO_SHARD, 1);
        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.setDisplayName(getDisplayName());
        
        // Store the spell name in persistent data
        PersistentDataContainer container = wandMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(EmpireWandPlus._plugin, "current_spell");
        container.set(key, PersistentDataType.STRING, Spells.get(0));
        
        wand.setItemMeta(wandMeta);
        return wand;
    }

    @Override
    public String getPermissionBase() {
        return EmpireWandPlus.PermissionPrefix + "elementos.";
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
