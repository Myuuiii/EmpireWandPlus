package moe.myuuiii.empirewandplus;

import moe.myuuiii.empirewandplus.commands.GetWandCommand;
import moe.myuuiii.empirewandplus.commands.GetWandCompleter;
import moe.myuuiii.empirewandplus.listeners.DamageListener;
import moe.myuuiii.empirewandplus.listeners.InteractListener;
import moe.myuuiii.empirewandplus.listeners.ProjectileListener;
import moe.myuuiii.empirewandplus.managers.ConfigManager;
import moe.myuuiii.empirewandplus.runnables.CloudRunnables;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    public static App _app;

    public App getApp() {
        return _app;
    }

    @Override
    public void onEnable() {
        App._app = this;
        getLogger().info("Empire wand plus plugin was enabled!");

        // Set up general configuration
        ConfigManager.setupConfig(this);

        // If enabled, load custom spell sets
        registerCustomSpellSets();
        getLogger().info("Spell sets loaded");

        registerCommands();
        registerRunnables();
        registerListeners();
    }

    public void registerCommands() {
        // Wand command
        getCommand("wand").setExecutor(new GetWandCommand());
        getCommand("wand").setTabCompleter(new GetWandCompleter());
    }

    public void registerListeners() {
        registerListeners(
                new InteractListener(),
                new ProjectileListener(),
                new DamageListener());
    }

    public void registerRunnables() {
        // Cloud runnables
        CloudRunnables cloudRunnables = new CloudRunnables();
        registerRunnables(
                cloudRunnables.new BloodCloudRunnable(),
                cloudRunnables.new CelestialCloudRunnable(),
                cloudRunnables.new EmpireCloudRunnable(),
                cloudRunnables.new PoisonCloudRunnable());
    }

    private static void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, _app);
        }
    }

    private static void registerRunnables(Runnable... runnables) {
        for (Runnable runnable : runnables) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(_app, runnable, 0L, 0L);
        }
    }

    public void registerCustomSpellSets() {
        if (ConfigManager.getUseCustomSpellSet(Data.empireWandConfigurationName)) {
            WandSpellLists.EmpireWandSpells = ConfigManager.getCustomWandSpells(Data.empireWandConfigurationName);
            getLogger().info(Data.prefix + "Empire wand uses a custom spell set");

        }
        if (ConfigManager.getUseCustomSpellSet(Data.bloodWandConfigurationName)) {
            WandSpellLists.BloodWandSpells = ConfigManager.getCustomWandSpells(Data.bloodWandConfigurationName);
            getLogger().info(Data.prefix + "Blood wand uses a custom spell set");

        }
        if (ConfigManager.getUseCustomSpellSet(Data.celestialWandConfigurationName)) {
            WandSpellLists.CelestialWandSpells = ConfigManager.getCustomWandSpells(Data.celestialWandConfigurationName);
            getLogger().info(Data.prefix + "Celestial wand uses a custom spell set");

        }
        if (ConfigManager.getUseCustomSpellSet(Data.scytheWandConfigurationName)) {
            WandSpellLists.PoisonScytheWandSpells = ConfigManager.getCustomWandSpells(Data.scytheWandConfigurationName);
            getLogger().info(Data.prefix + "Scythe wand uses a custom spell set");

        }
        if (ConfigManager.getUseCustomSpellSet(Data.hellWandConfigurationName)) {
            WandSpellLists.HellWandSpells = ConfigManager.getCustomWandSpells(Data.hellWandConfigurationName);
            getLogger().info(Data.prefix + "Hell wand uses a custom spell set");

        }
    }
}
