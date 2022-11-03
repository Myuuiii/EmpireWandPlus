package moe.myuuiii.empirewandplus.spells;

import moe.myuuiii.empirewandplus.Data;
import moe.myuuiii.empirewandplus.Spells;
import moe.myuuiii.empirewandplus.handlers.CloudHandler;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BloodCloudSpell {
	//
	// Settings
	//
	// No settings for this spell

	public static void Execute(Location loc, Player p) {

		if (Data.bloodCloudUsers.contains(p.getUniqueId())) {
			p.sendMessage(Data.prefix + ChatColor.GRAY + "Deactivated " + Spells.BloodCloud);
			if (!p.getGameMode().equals(GameMode.CREATIVE)) {
				p.setAllowFlight(false);
			}
			CloudHandler.DisableCloud(p.getUniqueId());
		} else {
			p.sendMessage(Data.prefix + ChatColor.GRAY + "Activated " + Spells.BloodCloud);
			if (!p.getGameMode().equals(GameMode.CREATIVE)) {
				p.setAllowFlight(true);
			}
			Data.bloodCloudUsers.add(p.getUniqueId());
		}
	}
}
