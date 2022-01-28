package moe.myuuiii.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class LightningSpellEffect {

	//
	// Settings
	//
	private static double _closeRange = 6;
	private static double _damage = 6;
	private static int _explosionSize = 3;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.lightningBolts.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						s.getWorld().strikeLightning(s.getLocation());
						s.getWorld().createExplosion(s.getLocation(), _explosionSize);
						final List<Entity> near = (List<Entity>) s.getWorld().getNearbyEntities(s.getLocation(),
								_closeRange, _closeRange, _closeRange);
						for (final Entity en : near) {
							if (en instanceof Damageable) {
								((Damageable) en).damage(_damage);
							}
						}
						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, s.getLocation(), 250, 0.1, 0.1, 0.1, 0.1);
					s.getWorld().spawnParticle(Particle.CLOUD, s.getLocation(), 125, 0.1, 0.1, 0.1, 0.2);
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);

	}
}
