package moe.myuuiii.empirewandplus.runnables;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.Data;

public class BloodCloudRunnable implements Runnable {

	//
	// Settings
	//
	private static int _particleDensityModifier = 3;

	@Override
	public void run() {
		for (UUID uuid : Data.bloodCloudUsers) {
			if (Bukkit.getPlayer(uuid) != null) {
				Player p = Bukkit.getPlayer(uuid);
				Location l = p.getLocation();
				l.add(0, 0.1, 0);
				p.getWorld().spawnParticle(Particle.REDSTONE, l, 5 * _particleDensityModifier, 0.3, 0.0, 0.3, 0,
						new DustOptions(Color.fromRGB(255, 0, 0), 2));
				p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, l, 10 * _particleDensityModifier, 0.3, 0.0, 0.3, 0);
			}
		}
	}

}
