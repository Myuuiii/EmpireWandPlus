package moe.myuuiii.empirewandplus.spellEffects;

import org.bukkit.Particle;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class FirePulseSpellEffect {
	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.firepulses.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						s.getWorld().createExplosion(s.getLocation(), 2, true);
						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.FLAME, s.getLocation(), 50, 1, 1, 1, 0.1);
					s.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, s.getLocation(), 25, 1, 1, 1, 0);
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);
	}
}
