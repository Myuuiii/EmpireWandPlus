package moe.myuuiii.empirewandplus.managers;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;
import net.md_5.bungee.api.ChatColor;

public class ConfigManager {

	private static FileConfiguration _config;

	public static void setupConfig(App app) {
		ConfigManager._config = app.getConfig();
		app.saveDefaultConfig();
	}

	public static boolean getWandEnabled(String wandName) {
		return _config.getBoolean("Wands." + wandName + ".Enabled");
	}

	public static List<String> getCustomWandSpells(String wandName) {
		return _config.getStringList("Wands." + wandName + ".Spells");
	}

	public static boolean getUseCustomSpellSet(String wandName) {
		return _config.getBoolean("Wands." + wandName + ".UseCustomSpellSet");
	}

	public static boolean getUseDefaultSpellSet() {
		return _config.getBoolean("UseDefaultSpellSets");
	}

	public static boolean getSpellEnabled(String spellName, Player p) {
		if (_config.getBoolean("Spells." + spellName + ".Enabled")) {
			return true;
		} else {
			p.sendMessage(
					Data.prefix + ChatColor.LIGHT_PURPLE + "[" + spellName + "]" + ChatColor.GRAY + " is disabled");
			return false;
		}
	}

	public static int getSpellRange(String spellName) {
		return _config.getInt("Spells." + spellName + ".Range");
	}
}
