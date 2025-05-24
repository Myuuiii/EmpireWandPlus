package com.myuuiii.empirewandplus.Managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import static com.myuuiii.empirewandplus.Extensions.colorText;

public class ConfigManager {
    private static FileConfiguration config;

    public static void initialize(Plugin plugin) {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public static String getWandPrefix(String wandType) {
        return colorText(config.getString("messages.prefix." + wandType.toLowerCase(), "&8[&7Wand&8]&r "));
    }

    public static String getWandDisplayName(String wandType) {
        return colorText(config.getString("messages.display-names." + wandType.toLowerCase(), "&7Generic Wand"));
    }

    public static String getErrorMessage(String key) {
        return colorText(config.getString("messages.errors." + key, "&cAn error occurred"));
    }

    public static String getSuccessMessage(String key) {
        return colorText(config.getString("messages.success." + key, "&7Operation successful"));
    }

    public static String getWandGivenMessage(String wandName, boolean startsWithVowel) {
        String article = startsWithVowel ? "an" : "a";
        return colorText(getSuccessMessage("wand-given")
                .replace("{article}", article)
                .replace("{wand-name}", wandName));
    }
}