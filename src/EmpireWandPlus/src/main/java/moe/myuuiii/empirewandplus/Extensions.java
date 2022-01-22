package moe.myuuiii.empirewandplus;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Extensions {
	public static boolean CheckIfInRange(int range, Location loc, Player player) {
		if (loc.distance(player.getLocation()) <= range)
			return true;
		return false;
	}
}
