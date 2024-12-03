package com.myuuiii.empirewandplus.Listeners;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Abstracts.ProjectileSpellEffect;
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
        for (ProjectileSpellEffect effect : EmpireWandPlus.spellEffectHashMap.values()) {
            effect.Execute(eventEntity);
        }
    }
}
