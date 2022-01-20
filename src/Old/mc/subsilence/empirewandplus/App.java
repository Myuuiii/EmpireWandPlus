package mc.subsilence.empirewandplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import mc.subsilence.empirewandplus.commands.*;
import mc.subsilence.empirewandplus.listeners.*;
import mc.subsilence.empirewandplus.runnables.*;

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

        // Cloud
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new CloudRunnable(), 10L, 0L);
    }
}
