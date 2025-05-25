package com.myuuiii.empirewandplus.Managers;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static com.myuuiii.empirewandplus.Extensions.colorText;

public class MessagesManager {
    private static FileConfiguration messages;

    public static void loadMessages(EmpireWandPlus plugin) {
        File messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public static String getWandCommandMessage(String key) {
        return colorText(messages.getString("commands.wand." + key, "&cMessage not found!"));
    }

    public static String getWandGivenMessage(String wandName, boolean startsWithVowel) {
        String template = messages.getString("commands.wand.given", "&7You have been given {article} {wand-name}");
        String article = startsWithVowel ? "an" : "a";
        return colorText(template.replace("{article}", article).replace("{wand-name}", wandName));
    }

    public static String getErrorMessage(String key) {
        return colorText(messages.getString("errors." + key, "&cError message not found!"));
    }

    public static String getWandPrefix(String wandType) {
        return colorText(messages.getString("prefix." + wandType, ""));
    }

    public static String getWandDisplayName(String wandType) {
        return colorText(messages.getString("display-names." + wandType, ""));
    }

    public static String getCloudEnabledMessage(String spellName) {
        String template = messages.getString("spells.cloud.enabled", "&a{spell-name} enabled");
        return colorText(template.replace("{spell-name}", spellName));
    }
    
    public static String getCloudDisabledMessage(String spellName) {
        String template = messages.getString("spells.cloud.disabled", "&c{spell-name} disabled");
        return colorText(template.replace("{spell-name}", spellName));
    }

    public static FileConfiguration getMessages() {
        return messages;
    }
}

