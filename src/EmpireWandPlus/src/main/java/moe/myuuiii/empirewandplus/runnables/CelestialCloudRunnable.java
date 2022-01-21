package moe.myuuiii.empirewandplus.runnables;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.Data;

public class CelestialCloudRunnable implements Runnable {

	//
	// Settings
	//
	private static int _particleDensityModifier = 3;

	@Override
	public void run() {
		for (UUID uuid : Data.celestialCloudUsers) {
			if (Bukkit.getPlayer(uuid) != null) {
				Player p = Bukkit.getPlayer(uuid);
				Location l = p.getLocation();
				l.add(0, 0.1, 0);
				p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 10 * _particleDensityModifier, 0.3, 0.0, 0.3,
						0);
				p.getWorld().spawnParticle(Particle.CLOUD, l, 10 * _particleDensityModifier, 0.3, 0.0, 0.3, 0);
			}
		}
	}

}
