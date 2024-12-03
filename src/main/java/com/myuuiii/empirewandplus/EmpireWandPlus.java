package com.myuuiii.empirewandplus;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Abstracts.ProjectileSpellEffect;
import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.Commands.WandCommand;
import com.myuuiii.empirewandplus.Commands.WandCommandCompleter;
import com.myuuiii.empirewandplus.Listeners.EntityDamagedByEntityEvent;
import com.myuuiii.empirewandplus.Listeners.FallDamageListener;
import com.myuuiii.empirewandplus.Listeners.InteractionListener;
import com.myuuiii.empirewandplus.Listeners.ProjectileListener;
import com.myuuiii.empirewandplus.SpellEffects.Cloud.KajCloudEffect;
import com.myuuiii.empirewandplus.Wands.BloodWand;
import com.myuuiii.empirewandplus.Wands.ElementosWand;
import com.myuuiii.empirewandplus.Wands.EmpireWand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

import static com.myuuiii.empirewandplus.Data.SpellEffectHashMap.getSpellEffectHashMap;
import static com.myuuiii.empirewandplus.Data.SpellHashMap.getSpellHashMap;
import static com.myuuiii.empirewandplus.Extensions.colorText;

public final class EmpireWandPlus extends JavaPlugin {

    public static EmpireWandPlus _plugin;
    public static HashMap<String, Spell> spellHashMap = getSpellHashMap();
    public static HashMap<String, ProjectileSpellEffect> spellEffectHashMap = getSpellEffectHashMap();
    public static String Prefix = colorText("&8[&6EmpireWandPlus&8]&r ");
    public static String PermissionPrefix = "empirewandplus.";

    public static HashMap<String, Wand> wandHashMap = new HashMap<>() {{
        put("empire", new EmpireWand());
        put("blood", new BloodWand());
        put("elementos", new ElementosWand());
    }};

    private static void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, _plugin);
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        _plugin = this;

        registerCommands();
        registerListeners(
                new InteractionListener(),
                new ProjectileListener(),
                new FallDamageListener(),
                new EntityDamagedByEntityEvent()
        );
        registerRunnables(
                new KajCloudEffect()
        );
    }

    public void registerCommands() {
        Objects.requireNonNull(getCommand("wand")).setExecutor(new WandCommand());
        Objects.requireNonNull(getCommand("wand")).setTabCompleter(new WandCommandCompleter());
    }

    private static void registerRunnables(Runnable... runnables) {
        for (Runnable runnable : runnables) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, runnable, 0L, 0L);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
