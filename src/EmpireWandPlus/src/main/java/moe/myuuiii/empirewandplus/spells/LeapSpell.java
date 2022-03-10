package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LeapSpell {
	public static void Execute(Location loc, Player p) {

		//
		// Settings
		//
		int velocityMultiplication = 2;
		int cloudParticleCount = 100;

		p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);
		p.getWorld().spawnParticle(Particle.CLOUD, loc, cloudParticleCount, 0, 0, 0, 0.1);

		p.setVelocity(p.getLocation().getDirection().multiply(velocityMultiplication));

	}
}
