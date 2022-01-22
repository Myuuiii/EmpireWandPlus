package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;
import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class FlameWaveSpell {

	//
	// Settings
	//
	private static int _rangeMultiplication = 1;

	public static void Execute(Location loc, Player p) {
		Snowball e = (Snowball) p.launchProjectile((Class<Snowball>) Snowball.class);

		e.setVelocity(e.getVelocity().multiply(_rangeMultiplication));
		e.setGravity(false);

		Data.flameUsers.add(p.getUniqueId());
		Data.flameWaves.add(e);

		new BukkitRunnable() {
			public void run() {
				if (!e.isDead()) {
					e.remove();
					Data.flameUsers.remove(p.getUniqueId());
				}
				if (Data.flameWaves.contains(e)) {
					Data.flameWaves.remove(e);
					Data.flameUsers.remove(p.getUniqueId());
				}
			}
		}.runTaskLater(App._app, 200L);
	}
}
