package moe.myuuiii.empirewandplus.spells;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

public class BloodWaveSpell {

	//
	// Settings
	//
	private static int _rangeMultiplication = 1;

	public static void Execute(Location loc, Player p) {
		Snowball e = (Snowball) p.launchProjectile((Class<Snowball>) Snowball.class);

		e.setVelocity(e.getVelocity().multiply(_rangeMultiplication));
		e.setGravity(false);

		Data.bloodUsers.add(p.getUniqueId());
		Data.bloodWaves.add(e);

		new BukkitRunnable() {
			public void run() {
				if (!e.isDead()) {
					e.remove();
					Data.bloodUsers.remove(p.getUniqueId());
				}
				if (Data.bloodWaves.contains(e)) {
					Data.bloodWaves.remove(e);
					Data.bloodUsers.remove(p.getUniqueId());
				}
			}
		}.runTaskLater(App._app, 200L);
	}
}
