package com.myuuiii.empirewandplus;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.Commands.WandCommand;
import com.myuuiii.empirewandplus.Commands.WandCommandCompleter;
import com.myuuiii.empirewandplus.Listeners.InteractionListener;
import com.myuuiii.empirewandplus.Listeners.ProjectileListener;
import com.myuuiii.empirewandplus.Spells.Capture.Capture;
import com.myuuiii.empirewandplus.Spells.Comet.EmpireComet;
import com.myuuiii.empirewandplus.Spells.Comet.FireComet;
import com.myuuiii.empirewandplus.Spells.Confuse.CelestialConfuse;
import com.myuuiii.empirewandplus.Spells.Confuse.EmpireConfuse;
import com.myuuiii.empirewandplus.Spells.Fireball.Fireball;
import com.myuuiii.empirewandplus.Spells.Ignite.Ignite;
import com.myuuiii.empirewandplus.Spells.Launch.Launch;
import com.myuuiii.empirewandplus.Spells.Leap.Leap;
import com.myuuiii.empirewandplus.Spells.Lightning.Lightning;
import com.myuuiii.empirewandplus.Spells.Pulse.FirePulse;
import com.myuuiii.empirewandplus.Spells.Smite.Smite;
import com.myuuiii.empirewandplus.Spells.Spark.BloodSpark;
import com.myuuiii.empirewandplus.Spells.Spark.EmpireSpark;
import com.myuuiii.empirewandplus.Spells.Spark.PoisonSpark;
import com.myuuiii.empirewandplus.Spells.Spark.Spark;
import com.myuuiii.empirewandplus.Spells.Stun.CelestialStun;
import com.myuuiii.empirewandplus.Spells.Stun.EmpireStun;
import com.myuuiii.empirewandplus.Spells.Wave.BloodWave;
import com.myuuiii.empirewandplus.Spells.Wave.FlameWave;
import com.myuuiii.empirewandplus.Spells.Wave.PoisonWave;
import com.myuuiii.empirewandplus.Wands.BloodWand;
import com.myuuiii.empirewandplus.Wands.EmpireWand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

import static com.myuuiii.empirewandplus.Extensions.colorText;

public final class EmpireWandPlus extends JavaPlugin {

    public static EmpireWandPlus _plugin;
    public static HashMap<String, Spell> spellHashMap = new HashMap<>() {{
        // Sparks
        put(SpellNames.Spark, new Spark());
        put(SpellNames.EmpireSpark, new EmpireSpark());
        put(SpellNames.BloodSpark, new BloodSpark());
        put(SpellNames.PoisonSpark, new PoisonSpark());

        // Waves
        put(SpellNames.BloodWave, new BloodWave());
        put(SpellNames.PoisonWave, new PoisonWave());
        put(SpellNames.FlameWave, new FlameWave());

        // Confuse
        put(SpellNames.EmpireConfuse, new EmpireConfuse());
        put(SpellNames.CelestialConfuse, new CelestialConfuse());

        // Stun
        put(SpellNames.EmpireStun, new EmpireStun());
        put(SpellNames.CelestialStun, new CelestialStun());

        // Capture
        put(SpellNames.Capture, new Capture());

        // Comet
        put(SpellNames.EmpireComet, new EmpireComet());
        put(SpellNames.FireComet, new FireComet());

        // Pulse
        put(SpellNames.FirePulse, new FirePulse());

        // Fireball
        put(SpellNames.Fireball, new Fireball());

        // Ignite
        put(SpellNames.Ignite, new Ignite());

        // Launch
        put(SpellNames.Launch, new Launch());

        // Leap
        put(SpellNames.Leap, new Leap());

        // Lightning
        put(SpellNames.Lightning, new Lightning());

        // Smite
        put(SpellNames.Smite, new Smite());
    }};

    public static String Prefix = colorText("&8[&6EmpireWand&8]&r ");
    public static String PermissionPrefix = "empirewandplus.";

    public static HashMap<String, Wand> wandHashMap = new HashMap<>() {{
        put("empire", new EmpireWand());
        put("blood", new BloodWand());
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
                new ProjectileListener()
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
