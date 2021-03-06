package moe.myuuiii.empirewandplus;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class Extensions {
	public static boolean CheckIfInRange(int range, Location loc, Player player) {
		return loc.distance(player.getLocation()) <= range;
	}

	public static String GetNextSpell(List<String> spellList, ItemStack wand, Player player) {
		int currentSpellIndex = spellList.indexOf(wand.getItemMeta().getLore().get(0));

		int modifier;

		if (player.isSneaking())
			modifier = -1;
		else
			modifier = 1;

		int nextSpellIndex = currentSpellIndex + modifier;

		if (nextSpellIndex >= spellList.size())
			nextSpellIndex = 0;
		else if (nextSpellIndex < 0)
			nextSpellIndex = spellList.size() - 1;

		return spellList.get(nextSpellIndex);
	}

	public static String colorText(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
}
