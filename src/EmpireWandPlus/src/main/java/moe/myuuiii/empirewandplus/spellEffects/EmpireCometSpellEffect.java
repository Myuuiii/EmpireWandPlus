package moe.myuuiii.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.EntityEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class EmpireCometSpellEffect {

	//
	// Settings
	//
	private static double _closeRange = 10;
	private static double _damage = 6;
	private static int _blindnessDuration = 50;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.empireComets.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						s.getWorld().createExplosion(s.getLocation(), 10, false);

						final List<Entity> near = (List<Entity>) s.getLocation().getWorld().getEntities();
						for (final Entity en : near) {
							if (en.getLocation().distance(s.getLocation()) <= _closeRange && en instanceof Damageable) {
								((Damageable) en).damage(_damage);
								if (en instanceof Player) {
									Player p = (Player) en;
									p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration,
											1, true, false));
								}
							}
						}

						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.SPELL_WITCH, s.getLocation(), 250, 1, 1, 1, 0.2);
					s.getWorld().spawnParticle(Particle.SMOKE_LARGE, s.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);
	}
}
