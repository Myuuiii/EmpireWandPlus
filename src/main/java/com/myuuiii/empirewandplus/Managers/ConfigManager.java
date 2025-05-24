package com.myuuiii.empirewandplus.Managers;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private static FileConfiguration config;

    public static void loadConfig(EmpireWandPlus plugin) {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public static FileConfiguration getConfig() {
        return config;
    }
}

