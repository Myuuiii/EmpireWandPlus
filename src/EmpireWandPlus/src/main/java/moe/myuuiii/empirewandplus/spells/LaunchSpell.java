package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Location;
import org.bukkit.Particle;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LaunchSpell {

	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static double _launchHeightModifier = 2;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.CLOUD, loc, 250, 1.5, 0, 1.5, 0.1);

		final List<Entity> near = (List<Entity>) loc.getWorld().getNearbyEntities(loc, _closeRange, _closeRange,
				_closeRange);
		for (final Entity en : near) {
			en.setVelocity(new Vector(p.getVelocity().getX(), _launchHeightModifier, p.getVelocity().getZ()));
		}
	}

}
