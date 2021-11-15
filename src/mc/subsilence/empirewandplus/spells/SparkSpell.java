package mc.subsilence.empirewandplus.spells;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.List;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SparkSpell {

	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static double _damage = 2.5;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc, 100, 0, 0, 0, 0.1);
		p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 100, 0, 0, 0, 0.1);
		p.getWorld().playSound(loc, Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 5, 0.9f);
		p.getWorld().playSound(loc, Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 5, 0.9f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getEntities();
		for (final Entity en : near) {
			if (en.getLocation().distance(loc) <= _closeRange && en instanceof Damageable) {
				((Damageable) en).damage(_damage);
			}
		}
	}
}
