package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;
import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class FireballSpell {

	//
	// Settings
	//
	private static int _rangeMultiplication = 3;

	public static void Execute(Location loc, Player p) {
		Snowball e = (Snowball) p.launchProjectile((Class) Snowball.class);

		e.setVelocity(e.getVelocity().multiply(_rangeMultiplication));
		e.setGravity(false);

		Data.fireballs.add(e);

		new BukkitRunnable() {
			public void run() {
				if (!e.isDead()) {

					e.remove();
				}
				if (Data.fireballs.contains(e)) {
					Data.fireballs.remove(e);
				}
			}
		}.runTaskLater(App._app, 200L);
	}
}
