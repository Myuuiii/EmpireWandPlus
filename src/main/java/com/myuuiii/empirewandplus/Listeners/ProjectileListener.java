package com.myuuiii.empirewandplus.Listeners;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.SpellEffects.SpellEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileListener implements Listener {
    @EventHandler
    public void onProjectileLaunch(final ProjectileLaunchEvent e) {
        Entity eventEntity = e.getEntity();
        if (eventEntity instanceof Firework) return;
        for (SpellEffect effect : EmpireWandPlus.spellEffectHashMap.values()) {
            effect.Execute(eventEntity);
        }

//        PoisonWaveEffect.Execute(eventEntity);
//        FlameWaveEffect.Execute(eventEntity);
//
//        EmpireCometEffect.Execute(eventEntity);
//        FireCometEffect.Execute(eventEntity);
//
//        FirePulseEffect.Execute(eventEntity);
//        EmpirePulseEffect.Execute(eventEntity);
//
//        FireballEffect.Execute(eventEntity);
//
//        LightningEffect.Execute(eventEntity);
//        SmiteEffect.Execute(eventEntity);
    }
}
