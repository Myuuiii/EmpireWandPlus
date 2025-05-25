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


public class BloodWand extends Wand {
    
    public final static String Identifier = "Blood";
    
    public static List<String> Spells = new ArrayList<>();

    public static void loadSpellsFromConfig() {
        Spells.clear();
        FileConfiguration config = com.myuuiii.empirewandplus.EmpireWandPlus._plugin.getConfig();
        List<String> configSpells = config.getStringList("wands.Blood.spells");
        if (configSpells != null && !configSpells.isEmpty()) {
            Spells.addAll(configSpells);
        } else {
            Spells.add(SpellNames.Spark);
            Spells.add(SpellNames.BloodSpark);
            Spells.add(SpellNames.BloodWave);
        }
    }

    @Override
    public String getDisplayName() {
        return MessagesManager.getWandDisplayName("blood");
    }

    @Override
    public String getPrefix() {
        return MessagesManager.getWandPrefix("blood");
    }

    @Override
    protected Material getWandMaterial() {
        return Material.NETHER_WART;
    }
    
    @Override
    protected List<String> getSpellList() {
        return Spells;
    }

    @Override
    public String getPermissionBase() {
        return EmpireWandPlus.PermissionPrefix + "blood.";
    }

    @Override
    public void Handle(PlayerInteractEvent e) {
        final BloodWand bloodWand = (BloodWand) EmpireWandPlus.wandHashMap.get("blood");
        HandleInteraction(e, bloodWand);
    }

    @Override
    public void SwitchEffects(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 10.0f, 1.0f);
        p.getWorld().spawnParticle(Particle.ENCHANT, p.getLocation(), 50, 0.4, 0.5, 0.4, 0.0);
        p.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, p.getLocation().add(0, 0.3, 0), 50, 0.3, 0.6, 0.3, 0.1,
                Material.REDSTONE_BLOCK.createBlockData());
    }
}
