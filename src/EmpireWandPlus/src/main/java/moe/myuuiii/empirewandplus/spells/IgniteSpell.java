package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import static moe.myuuiii.empirewandplus.Extensions.getNearbyEntities;

public class IgniteSpell {
	//
	// Settings
	//
	private static double _closeRange = 4.0;
	private static int _fireDuration = 150;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 100, 1, 1, 1, 0.1);
		p.getWorld().spawnParticle(Particle.FLAME, loc, 75, 1, 1, 1, 0.3);
		p.getWorld().spawnParticle(Particle.LAVA, loc, 75, 1, 1, 1, 1);

		p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_SHOOT, 5, 0.85f);
		p.getWorld().playSound(loc, Sound.BLOCK_FURNACE_FIRE_CRACKLE, 5, 0.85f);

		final List<Entity> near = getNearbyEntities(_closeRange, loc);
		for (final Entity en : near) {
			if (en instanceof LivingEntity) {
				en.setFireTicks(_fireDuration);
			}
		}
	}
}
