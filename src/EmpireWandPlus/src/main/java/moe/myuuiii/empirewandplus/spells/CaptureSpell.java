package moe.myuuiii.empirewandplus.spells;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CaptureSpell {

	//
	// Settings
	//
	private static double _closeRange = 3.0;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, loc, 250, 1, 1, 1, 0.1);
		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 100, 1, 1, 1, 0);

		p.getWorld().playSound(loc, Sound.ENTITY_PIG_SADDLE, 5, 0.85f);
		p.getWorld().playSound(loc, Sound.ENTITY_SHULKER_TELEPORT, 5, 1f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getNearbyEntities(loc, _closeRange, _closeRange,
				_closeRange);
		for (final Entity en : near) {

			if (en.getLocation().distance(loc) <= _closeRange) {
				if (!(en instanceof Player player))
					return;

				if (player.equals(p))
					return;

				p.addPassenger(player);
			}
		}
	}
}
