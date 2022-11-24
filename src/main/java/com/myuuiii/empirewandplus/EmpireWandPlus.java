package com.myuuiii.empirewandplus;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.Commands.WandCommand;
import com.myuuiii.empirewandplus.Commands.WandCommandCompleter;
import com.myuuiii.empirewandplus.Listeners.EntityDamagedByEntityEvent;
import com.myuuiii.empirewandplus.Listeners.FallDamageListener;
import com.myuuiii.empirewandplus.Listeners.InteractionListener;
import com.myuuiii.empirewandplus.Listeners.ProjectileListener;
import com.myuuiii.empirewandplus.Abstracts.SpellEffect;
import com.myuuiii.empirewandplus.Wands.BloodWand;
import com.myuuiii.empirewandplus.Wands.ElementosWand;
import com.myuuiii.empirewandplus.Wands.EmpireWand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

import static com.myuuiii.empirewandplus.Extensions.colorText;
import static com.myuuiii.empirewandplus.Data.SpellEffectHashMap.getSpellEffectHashMap;
import static com.myuuiii.empirewandplus.Data.SpellHashMap.getSpellHashMap;

public final class EmpireWandPlus extends JavaPlugin {

    public static EmpireWandPlus _plugin;
    public static HashMap<String, Spell> spellHashMap = getSpellHashMap();
    public static HashMap<String, SpellEffect> spellEffectHashMap = getSpellEffectHashMap();

    public static String Prefix = colorText("&8[&6EmpireWand&8]&r ");
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
        this._plugin = this;

        registerCommands();
        registerListeners(
                new InteractionListener(),
                new ProjectileListener(),
                new FallDamageListener(),
                new EntityDamagedByEntityEvent()
        );
    }

    public void registerCommands() {
        getCommand("wand").setExecutor(new WandCommand());
        getCommand("wand").setTabCompleter(new WandCommandCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
