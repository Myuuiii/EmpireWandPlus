package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Location;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

public class LightningSpell {

	//
	// Settings
	//
	private static int _rangeMultiplication = 3;

	public static void Execute(Location loc, Player p) {
		Snowball e = (Snowball) p.launchProjectile((Class<Snowball>) Snowball.class);

		e.setVelocity(e.getVelocity().multiply(_rangeMultiplication));
		e.setGravity(false);

		Data.lightningBolts.add(e);

		new BukkitRunnable() {
			public void run() {
				if (!e.isDead()) {
					e.remove();
				}
				if (Data.lightningBolts.contains(e)) {
					Data.lightningBolts.remove(e);
				}
			}
		}.runTaskLater(App._app, 200L);
	}
}
