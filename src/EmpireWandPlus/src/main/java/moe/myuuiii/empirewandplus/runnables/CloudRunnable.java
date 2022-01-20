package moe.myuuiii.empirewandplus.runnables;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.Data;

public class CloudRunnable implements Runnable {

	//
	// Settings
	//
	private static int _particleDensityModifier = 3;

	@Override
	public void run() {
		for (UUID uuid : Data.cloudUsers) {
			if (Bukkit.getPlayer(uuid) != null) {
				Player p = Bukkit.getPlayer(uuid);
				Location l = p.getLocation();
				l.add(0, 0.1, 0);
				p.getWorld().spawnParticle(Particle.CLOUD, l, 10 * _particleDensityModifier, 0.3, 0.0, 0.3, 0.01);
				p.getWorld().spawnParticle(Particle.SNOWFLAKE, l, 10 * _particleDensityModifier, 0.3, 0.0, 0.3, 0);
			}
		}
	}

}
