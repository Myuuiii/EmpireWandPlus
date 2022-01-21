package moe.myuuiii.empirewandplus.spellEffects;

import org.bukkit.Particle;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class FireballSpellEffect {
	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.fireballs.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						s.getWorld().createExplosion(s.getLocation(), 10, true);
						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.FLAME, s.getLocation(), 50, 1, 1, 1, 0.2);
					s.getWorld().spawnParticle(Particle.SMOKE_LARGE, s.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
					s.getWorld().spawnParticle(Particle.LAVA, s.getLocation(), 50, 1, 1, 1, 0.2);
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);
	}
}
