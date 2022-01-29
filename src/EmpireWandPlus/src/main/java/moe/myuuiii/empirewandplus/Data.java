package moe.myuuiii.empirewandplus;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.*;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
	public static final List<String> wandTypes = Arrays
			.asList("empire", "blood", "scythe", "celestial", "hell");

	public static final String empireWandName = ChatColor.GOLD + "Empire wand";
	public static final String empireWandConfigurationName = "EmpireWand";

	public static final String bloodWandName = ChatColor.RED + "Blood wand";
	public static final String bloodWandConfigurationName = "BloodWand";

	public static final String scytheWandName = ChatColor.GREEN + "Poison scythe wand";
	public static final String scytheWandConfigurationName = "ScytheWand";

	public static final String celestialWandName = ChatColor.LIGHT_PURPLE + "Celestial Wand";
	public static final String celestialWandConfigurationName = "CelestialWand";

	public static final String hellWandName = ChatColor.RED + "Hell Wand";
	public static final String hellWandConfigurationName = "HellWand";

	public static final String prefix = Extensions.colorText("&7[&dX&7]&r ");
	public static final String currentSpellMessage = Extensions.colorText("&7Current Spell: &d");
	public static final String unknownSpellMessage = Extensions
			.colorText("&7The current spell was not recognized, maybe you have not selected one yet?");
	public static final String wandNotImplementedYet = "This wand has not yet been implemented";

	public static final String wandDisabled = Extensions.colorText("&cThis wand has been disabled");

	public static final ArrayList<Snowball> fireballs = new ArrayList<>();
	public static final ArrayList<Snowball> firepulses = new ArrayList<>();

	public static final ArrayList<Snowball> lightningBolts = new ArrayList<>();
	public static final ArrayList<Snowball> smites = new ArrayList<>();

	public static final ArrayList<Snowball> empireComets = new ArrayList<>();

	public static final ArrayList<UUID> bloodCloudUsers = new ArrayList<>();
	public static final ArrayList<UUID> celestialCloudUsers = new ArrayList<>();
	public static final ArrayList<UUID> empireCloudUsers = new ArrayList<>();
	public static final ArrayList<UUID> poisonCloudUsers = new ArrayList<>();

	public static final ArrayList<Snowball> poisonWaves = new ArrayList<>();
	public static final ArrayList<UUID> poisonUsers = new ArrayList<>();

	public static final ArrayList<Snowball> bloodWaves = new ArrayList<>();
	public static final ArrayList<UUID> bloodUsers = new ArrayList<>();

	public static final ArrayList<Snowball> flameWaves = new ArrayList<>();
	public static final ArrayList<UUID> flameUsers = new ArrayList<>();
}
