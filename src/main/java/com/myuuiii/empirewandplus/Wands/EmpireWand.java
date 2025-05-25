package com.myuuiii.empirewandplus.Wands;

import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.Data.SpellNames;
import com.myuuiii.empirewandplus.Managers.MessagesManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;


public class EmpireWand extends Wand {

    @Override
    public String getIdentifier() {
        return "Empire";
    }

    @Override
    protected void addDefaultSpells() {
        spells.add(SpellNames.Spark);
        spells.add(SpellNames.EmpireSpark);
        spells.add(SpellNames.BloodSpark);
        spells.add(SpellNames.PoisonSpark);
        spells.add(SpellNames.BloodWave);
        spells.add(SpellNames.PoisonWave);
        spells.add(SpellNames.FlameWave);
        spells.add(SpellNames.EmpireConfuse);
        spells.add(SpellNames.CelestialConfuse);
        spells.add(SpellNames.EmpireStun);
        spells.add(SpellNames.CelestialStun);
        spells.add(SpellNames.Capture);
        spells.add(SpellNames.EmpireComet);
        spells.add(SpellNames.FireComet);
        spells.add(SpellNames.FirePulse);
        spells.add(SpellNames.EmpirePulse);
        spells.add(SpellNames.Fireball);
        spells.add(SpellNames.Ignite);
        spells.add(SpellNames.Launch);
        spells.add(SpellNames.Leap);
        spells.add(SpellNames.Lightning);
        spells.add(SpellNames.Smite);
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
    public void SwitchEffects(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 10.0f, 1.0f);
        p.getWorld().spawnParticle(Particle.ENCHANT, p.getLocation(), 50, 0.4, 0.5, 0.4, 0.0);
        p.getWorld().spawnParticle(Particle.WITCH, p.getLocation(), 100, 0, 0.7, 0, 0.01);
    }
}
