package moe.myuuiii.empirewandplus.managers;

import org.bukkit.configuration.file.FileConfiguration;

import moe.myuuiii.empirewandplus.App;

public class ConfigManager {

	private static FileConfiguration _config;

	public static void setupConfig(App app) {
		ConfigManager._config = app.getConfig();
		app.saveDefaultConfig();
	}

	public static boolean getWandEnabled(String wandName) {
		return _config.getBoolean("Wands." + wandName + ".Enabled");
	}

	public static boolean getUseDefaultSpellSet() {
		return _config.getBoolean("UseDefaultSpellSets");
	}
}
