package moe.myuuiii.empirewandplus.spells;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

public class SmiteSpell {

	//
	// Settings
	//
	private static int _rangeMultiplication = 1;

	public static void Execute(Location loc, Player p) {
		Snowball e = p.launchProjectile(Snowball.class);

		e.setVelocity(e.getVelocity().multiply(_rangeMultiplication));
		e.setGravity(false);

		Data.smites.add(e);

		new BukkitRunnable() {
			public void run() {
				if (!e.isDead())
					e.remove();

				Data.smites.remove(e);
			}
		}.runTaskLater(App._app, 200L);
	}
}
