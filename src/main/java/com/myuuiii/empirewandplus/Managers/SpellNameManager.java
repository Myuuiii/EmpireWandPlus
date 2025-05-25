package com.myuuiii.empirewandplus.Managers;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SpellNameManager {
    private static final Map<String, String> spellDisplayNames = new HashMap<>();

    public static void load(EmpireWandPlus plugin) {
        File spellNamesFile = new File(plugin.getDataFolder(), "spellNames.yml");
        if (!spellNamesFile.exists()) {
            plugin.saveResource("spellNames.yml", false);
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(spellNamesFile);
        for (String key : config.getKeys(false)) {
            spellDisplayNames.put(key, config.getString(key));
        }
    }

    public static String getDisplayName(String configName) {
        return spellDisplayNames.getOrDefault(configName, configName);
    }
}

