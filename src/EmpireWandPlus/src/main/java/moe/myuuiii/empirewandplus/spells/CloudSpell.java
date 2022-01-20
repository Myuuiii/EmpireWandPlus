package moe.myuuiii.empirewandplus.spells;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.Data;
import net.md_5.bungee.api.ChatColor;

public class CloudSpell {
	//
	// Settings
	//
	// No settings for this spell

	public static void Execute(Location loc, Player p) {

		if (Data.cloudUsers.contains(p.getUniqueId())) {
			p.sendMessage(Data.prefix + ChatColor.GRAY + "Deactivated cloud flight");
			if (!p.getGameMode().equals(GameMode.CREATIVE)) {
				p.setAllowFlight(false);
			}
			Data.cloudUsers.remove(p.getUniqueId());
		} else {
			p.sendMessage(Data.prefix + ChatColor.GRAY + "Activated cloud flight");
			if (!p.getGameMode().equals(GameMode.CREATIVE)) {
				p.setAllowFlight(true);
			}
			Data.cloudUsers.add(p.getUniqueId());
		}
	}
}
