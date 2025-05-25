package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.Data.SpellNames;
import com.myuuiii.empirewandplus.Managers.MessagesManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;


public class BloodWand extends Wand {
    
    @Override
    public String getIdentifier() {
        return "Blood";
    }

    @Override
    protected void addDefaultSpells() {
        spells.add(SpellNames.Spark);
        spells.add(SpellNames.BloodSpark);
        spells.add(SpellNames.BloodWave);
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
    public void SwitchEffects(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 10.0f, 1.0f);
        p.getWorld().spawnParticle(Particle.ENCHANT, p.getLocation(), 50, 0.4, 0.5, 0.4, 0.0);
        p.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, p.getLocation().add(0, 0.3, 0), 50, 0.3, 0.6, 0.3, 0.1,
                Material.REDSTONE_BLOCK.createBlockData());
    }
}
