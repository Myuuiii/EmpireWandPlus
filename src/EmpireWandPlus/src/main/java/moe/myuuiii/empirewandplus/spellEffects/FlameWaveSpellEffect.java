package moe.myuuiii.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class FlameWaveSpellEffect {

	//
	// Settings
	//
	private static double _closeRange = 4;
	private static int _fireTickDuration = 300;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.flameWaves.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.FLAME, s.getLocation(), 125, 0.5, 0.5, 0.5, 0.1);
					s.getWorld().spawnParticle(Particle.SMOKE_LARGE, s.getLocation(), 125, 0.5, 0.5, 0.5, 0.1);

					s.getWorld().playSound(s.getLocation(), Sound.ENTITY_DROWNED_DEATH, 1, 0.65f);

					final List<Entity> near = (List<Entity>) s.getWorld().getNearbyEntities(s.getLocation(),
							_closeRange, _closeRange, _closeRange);
					for (final Entity en : near) {
						if (en instanceof Damageable) {
							if (en instanceof LivingEntity) {
								LivingEntity targetEntity = (LivingEntity) en;

								if (en instanceof Player) {
									Player p = (Player) en;
									if (Data.flameUsers.contains(p.getUniqueId())) {
										continue;
									}
								}
								targetEntity.setFireTicks(_fireTickDuration);
							}
						}
					}
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);
	}
}
