package moe.myuuiii.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class FireCometSpellEffect {

	//
	// Settings
	//
	private static double _closeRange = 10;
	private static double _damage = 6;
	private static int _fireTicks = 50;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.firecomets.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						s.getWorld().createExplosion(s.getLocation(), 10, false);

						final List<Entity> near = (List<Entity>) s.getWorld().getNearbyEntities(s.getLocation(),
								_closeRange, _closeRange, _closeRange);
						for (final Entity en : near) {
							if (en instanceof LivingEntity targetEntity) {
								targetEntity.damage(_damage);
								targetEntity.setFireTicks(_fireTicks);
							}
						}
						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.FLAME, s.getLocation(), 250, 1, 1, 1, 0.05);
					s.getWorld().spawnParticle(Particle.SMOKE_LARGE, s.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);
	}
}
