package com.myuuiii.empirewandplus.Abstracts;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public abstract class SpellEffect {
    public void Execute(Entity entity) {
        Spell spell = getSpell();
        new BukkitRunnable() {
            public void run() {
                if (getSpellEntityList().contains(entity)) {
                    if (entity.isDead()) {
                        OnDeath(entity, spell);
                        this.cancel();
                    }
                    WhileAlive(entity, spell);
                }
            }
        }.runTaskTimer(EmpireWandPlus._plugin, 0L, 1L);
    }

    public abstract Spell getSpell();

    public abstract void WhileAlive(Entity entity, Spell spell);
    public abstract void OnDeath(Entity entity, Spell spell);
    public abstract ArrayList<Entity> getSpellEntityList();
}
