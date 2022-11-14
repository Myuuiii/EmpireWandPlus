package com.myuuiii.empirewandplus.Listeners;

import com.myuuiii.empirewandplus.SpellEffects.Comet.EmpireCometEffect;
import com.myuuiii.empirewandplus.SpellEffects.Comet.FireCometEffect;
import com.myuuiii.empirewandplus.SpellEffects.Fireball.FireballEffect;
import com.myuuiii.empirewandplus.SpellEffects.Lightning.LightningEffect;
import com.myuuiii.empirewandplus.SpellEffects.Pulse.EmpirePulseEffect;
import com.myuuiii.empirewandplus.SpellEffects.Pulse.FirePulseEffect;
import com.myuuiii.empirewandplus.SpellEffects.Smite.SmiteEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.BloodWaveEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.FlameWaveEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.PoisonWaveEffect;
import com.myuuiii.empirewandplus.Spells.Pulse.EmpirePulse;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileListener implements Listener {
    @EventHandler
    public void onProjectileLaunch(final ProjectileLaunchEvent e) {
        Entity eventEntity = e.getEntity();
// This does not work lol
//        for (Spell spell : EmpireWandPlus.spellHashMap.values())
//        {
//            if (spell instanceof IRangedSpell rangedSpell)
//            {
//                rangedSpell.ExecuteSpellTrail(eventEntity);
//            }
//        }

        BloodWaveEffect.Execute(eventEntity);
        PoisonWaveEffect.Execute(eventEntity);
        FlameWaveEffect.Execute(eventEntity);

        EmpireCometEffect.Execute(eventEntity);
        FireCometEffect.Execute(eventEntity);

        FirePulseEffect.Execute(eventEntity);
        EmpirePulseEffect.Execute(eventEntity);

        FireballEffect.Execute(eventEntity);

        LightningEffect.Execute(eventEntity);
        SmiteEffect.Execute(eventEntity);
    }
}
