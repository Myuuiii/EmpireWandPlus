package moe.myuuiii.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class SmiteSpellEffect {

	//
	// Settings
	//
	private static double _closeRange = 10;
	private static double _damage = 19;
	private static int _explosionSize = 10;
	private static int _lightningStrikeRadius = 5;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.smites.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed

						for (Integer x = -_lightningStrikeRadius; x <= _lightningStrikeRadius; x++) {
							for (Integer z = -_lightningStrikeRadius; z <= _lightningStrikeRadius; z++) {
								s.getWorld().strikeLightning(s.getLocation().add(x, 0, z));
							}
						}

						s.getWorld().createExplosion(s.getLocation(), _explosionSize, true);

						final List<Entity> near = (List<Entity>) s.getWorld().getNearbyEntities(s.getLocation(), _closeRange, _closeRange, _closeRange);
						for (final Entity en : near) {

							if(en instanceof Damageable targetEntity) targetEntity.damage(_damage);

						}
						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, s.getLocation(), 75, 0.1, 0.1, 0.1, 0.1);
					s.getWorld().spawnParticle(Particle.CLOUD, s.getLocation(), 75, 0.1, 0.1, 0.1, 0.2);
					s.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, s.getLocation(), 75, 0.1, 0.1, 0.1, 0.1);
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);

	}
}
