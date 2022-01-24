package moe.myuuiii.empirewandplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import moe.myuuiii.empirewandplus.commands.*;
import moe.myuuiii.empirewandplus.listeners.*;
import moe.myuuiii.empirewandplus.managers.ConfigManager;
import moe.myuuiii.empirewandplus.runnables.*;
import net.md_5.bungee.api.ChatColor;

public class App extends JavaPlugin {

    public static App _app;

    public App getApp() {
        return _app;
    }

    @Override
    public void onEnable() {
        App._app = this;
        System.out.println("Empire wand plus plugin was enabled!");

        // Set up general configuration
        ConfigManager.setupConfig(this);

        // If enabled, load custom spell sets
        registerCustomSpellSets();
        System.out.println("Spell sets loaded");

        registerCommands();
        registerEvents();
        registerRunnables();
    }

    public void registerCommands() {
        // Wand command
        getCommand("wand").setExecutor(new GetWandCommand());
        getCommand("wand").setTabCompleter(new GetWandCompleter());
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectileListener(), this);
    }

    public void registerRunnables() {

        // Cloud runnables
        CloudRunnables cloudRunnables = new CloudRunnables();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, cloudRunnables.new BloodCloudRunnable(), 10L, 0L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, cloudRunnables.new CelestialCloudRunnable(), 10L, 0L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, cloudRunnables.new EmpireCloudRunnable(), 10L, 0L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, cloudRunnables.new PoisonCloudRunnable(), 10L, 0L);
    }

    public void registerCustomSpellSets() {
        if (ConfigManager.getUseCustomSpellSet(Data.empireWandConfigurationName)) {
            WandSpellLists.EmpireWandSpells = ConfigManager.getCustomWandSpells(Data.empireWandConfigurationName);
            System.out.println(Data.prefix + "Empire wand uses a custom spell set");

        }
        if (ConfigManager.getUseCustomSpellSet(Data.bloodWandConfigurationName)) {
            WandSpellLists.BloodWandSpells = ConfigManager.getCustomWandSpells(Data.bloodWandConfigurationName);
            System.out.println(Data.prefix + "Blood wand uses a custom spell set");

        }
        if (ConfigManager.getUseCustomSpellSet(Data.celestialWandConfigurationName)) {
            WandSpellLists.CelestialWandSpells = ConfigManager.getCustomWandSpells(Data.celestialWandConfigurationName);
            System.out.println(Data.prefix + "Celestial wand uses a custom spell set");

        }
        if (ConfigManager.getUseCustomSpellSet(Data.scytheWandConfigurationName)) {
            WandSpellLists.PoisonScytheWandSpells = ConfigManager.getCustomWandSpells(Data.scytheWandConfigurationName);
            System.out.println(Data.prefix + "Scythe wand uses a custom spell set");

        }
        if (ConfigManager.getUseCustomSpellSet(Data.hellWandConfigurationName)) {
            WandSpellLists.HellWandSpells = ConfigManager.getCustomWandSpells(Data.hellWandConfigurationName);
            System.out.println(Data.prefix + "Hell wand uses a custom spell set");

        }
    }
}
