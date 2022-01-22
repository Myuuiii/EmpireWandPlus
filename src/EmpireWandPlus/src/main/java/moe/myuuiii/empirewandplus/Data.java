package moe.myuuiii.empirewandplus;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.*;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
	public static List<String> wandTypes = Arrays
			.asList(new String[] { "empire", "blood", "scythe", "celestial", "hell" });

	public static String empireWandName = ChatColor.GOLD + "Empire wand";
	public static String bloodWandName = ChatColor.RED + "Blood wand";
	public static String scytheWandName = ChatColor.GREEN + "Poison scythe wand";
	public static String celestialWandName = ChatColor.LIGHT_PURPLE + "Celestial Wand";
	public static String hellWandName = ChatColor.RED + "Hell Wand";

	public static String prefix = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "X" + ChatColor.GRAY + "] "
			+ ChatColor.RESET;
	public static String currentSpellMessage = ChatColor.GRAY + "Current Spell: " + ChatColor.LIGHT_PURPLE;
	public static String unknownSpellMessage = ChatColor.GRAY
			+ "The current spell was not recognized, maybe you have not selected one yet?";
	public static String wandNotImplementedYet = "This wand has not yet been implemented";

	public static ArrayList<Snowball> fireballs = new ArrayList<>();
	public static ArrayList<Snowball> firepulses = new ArrayList<>();

	public static ArrayList<Snowball> lightningBolts = new ArrayList<>();
	public static ArrayList<Snowball> smites = new ArrayList<>();

	public static ArrayList<Snowball> empireComets = new ArrayList<>();

	public static ArrayList<UUID> bloodCloudUsers = new ArrayList<>();
	public static ArrayList<UUID> celestialCloudUsers = new ArrayList<>();
	public static ArrayList<UUID> empireCloudUsers = new ArrayList<>();
	public static ArrayList<UUID> poisonCloudUsers = new ArrayList<>();

	public static ArrayList<Snowball> poisonWaves = new ArrayList<>();
	public static ArrayList<UUID> poisonUsers = new ArrayList<>();

	public static ArrayList<Snowball> bloodWaves = new ArrayList<>();
	public static ArrayList<UUID> bloodUsers = new ArrayList<>();

	public static ArrayList<Snowball> flameWaves = new ArrayList<>();
	public static ArrayList<UUID> flameUsers = new ArrayList<>();
}
