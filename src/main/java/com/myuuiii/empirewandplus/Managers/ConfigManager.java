package com.myuuiii.empirewandplus.Managers;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private static FileConfiguration config;
    private static FileConfiguration messages;

    public static void loadConfigs(EmpireWandPlus plugin) {
        // Load config.yml
        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        // Load messages.yml
        File messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public static String getWandCommandMessage(String key) {
        return messages.getString("commands.wand." + key, "&cMessage not found!");
    }

    public static String getWandGivenMessage(String wandName, boolean startsWithVowel) {
        String template = messages.getString("commands.wand.given", "&7You have been given {article} {wand-name}");
        String article = startsWithVowel ? "an" : "a";
        return template.replace("{article}", article).replace("{wand-name}", wandName);
    }

    // Add other getters for messages as needed, e.g. errors, prefixes, etc.
    public static String getErrorMessage(String key) {
        return messages.getString("errors." + key, "&cError message not found!");
    }

    public static String getWandPrefix(String wandType) {
        return messages.getString("prefix." + wandType, "");
    }

    public static String getWandDisplayName(String wandType) {
        return messages.getString("display-names." + wandType, "");
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getMessages() {
        return messages;
    }
}