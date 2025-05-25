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


public class EmpireWand extends Wand {

    public final static String Identifier = "Empire";

    public static List<String> Spells = new ArrayList<>();

    public static void loadSpellsFromConfig() {
        Spells.clear();
        FileConfiguration config = com.myuuiii.empirewandplus.EmpireWandPlus._plugin.getConfig();
        List<String> configSpells = config.getStringList("wands.Empire.spells");
        if (configSpells != null && !configSpells.isEmpty()) {
            Spells.addAll(configSpells);
        } else {
            Spells.add(SpellNames.Spark);
            Spells.add(SpellNames.EmpireSpark);
            Spells.add(SpellNames.BloodSpark);
            Spells.add(SpellNames.PoisonSpark);
            Spells.add(SpellNames.BloodWave);
            Spells.add(SpellNames.PoisonWave);
            Spells.add(SpellNames.FlameWave);
            Spells.add(SpellNames.EmpireConfuse);
            Spells.add(SpellNames.CelestialConfuse);
            Spells.add(SpellNames.EmpireStun);
            Spells.add(SpellNames.CelestialStun);
            Spells.add(SpellNames.Capture);
            Spells.add(SpellNames.EmpireComet);
            Spells.add(SpellNames.FireComet);
            Spells.add(SpellNames.FirePulse);
            Spells.add(SpellNames.EmpirePulse);
            Spells.add(SpellNames.Fireball);
            Spells.add(SpellNames.Ignite);
            Spells.add(SpellNames.Launch);
            Spells.add(SpellNames.Leap);
            Spells.add(SpellNames.Lightning);
            Spells.add(SpellNames.Smite);
        }
    }

    @Override
    public String getDisplayName() {
        return MessagesManager.getWandDisplayName("empire");
    }

    @Override
    public String getPrefix() {
        return MessagesManager.getWandPrefix("empire");
    }

    @Override
    protected Material getWandMaterial() {
        return Material.BLAZE_ROD;
    }
    
    @Override
    protected List<String> getSpellList() {
        return Spells;
    }

    @Override
    public String getPermissionBase() {
        return EmpireWandPlus.PermissionPrefix + "empire.";
    }

    @Override
    public void Handle(PlayerInteractEvent e) {
        final EmpireWand empireWand = (EmpireWand) EmpireWandPlus.wandHashMap.get("empire");
        HandleInteraction(e, empireWand);
    }

    @Override
    public void SwitchEffects(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 10.0f, 1.0f);
        p.getWorld().spawnParticle(Particle.ENCHANT, p.getLocation(), 50, 0.4, 0.5, 0.4, 0.0);
        p.getWorld().spawnParticle(Particle.WITCH, p.getLocation(), 100, 0, 0.7, 0, 0.01);
    }
}
