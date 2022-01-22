package moe.myuuiii.empirewandplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import moe.myuuiii.empirewandplus.commands.*;
import moe.myuuiii.empirewandplus.listeners.*;
import moe.myuuiii.empirewandplus.runnables.*;

public class App extends JavaPlugin {

    public static App _app;

    public App getApp() {
        return _app;
    }

    @Override
    public void onEnable() {
        App._app = this;
        System.out.println("Empire wand plus plugin was enabled!");

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
}
