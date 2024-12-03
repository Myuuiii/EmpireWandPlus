package com.myuuiii.empirewandplus.Abstracts;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Interfaces.IProjectileSpell;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class ProjectileSpell extends Spell implements IProjectileSpell {

    public abstract int getRangeMultiplication();

    public abstract void AddProjectileToList(Entity e);

    public abstract void RemoveProjectileFromList(Entity e);

    public void Execute(Location loc, Player p) {
        Snowball e = p.launchProjectile(Snowball.class);
        AddProjectileToList(e);
        AddPlayerToList(p);
        e.setVelocity(e.getVelocity().multiply(getRangeMultiplication()));
        e.setGravity(false);
        e.setSilent(true);

        new BukkitRunnable() {
            public void run() {
                if (!e.isDead())
                    e.remove();
                RemoveProjectileFromList(e);
                RemovePlayerFromList(p);
            }
        }.runTaskLater(EmpireWandPlus._plugin, 200L);
    }

    public abstract void AddPlayerToList(Player p);

    public abstract void RemovePlayerFromList(Player p);
}
