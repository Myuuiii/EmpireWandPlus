package moe.myuuiii.empirewandplus;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.*;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
	public static List<String> wandTypes = Arrays.asList(new String[] { "empire", "blood" });

	public static String empireWandName = ChatColor.GOLD + "Empire wand";
	public static String bloodWandName = ChatColor.RED + "Blood wand";

	public static String prefix = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "X" + ChatColor.GRAY + "] "
			+ ChatColor.RESET;
	public static String currentSpellMessage = ChatColor.GRAY + "Current Spell: " + ChatColor.LIGHT_PURPLE;
	public static String unknownSpellMessage = ChatColor.GRAY
			+ "The current spell was not recognized, maybe you have not selected one yet?";

	public static ArrayList<Snowball> fireballs = new ArrayList<Snowball>();
	public static ArrayList<Snowball> lightningBolts = new ArrayList<Snowball>();
	public static ArrayList<Snowball> empireComets = new ArrayList<Snowball>();

	public static ArrayList<Snowball> poisonWaves = new ArrayList<Snowball>();
	public static ArrayList<UUID> poisonUsers = new ArrayList<UUID>();

	public static ArrayList<Snowball> bloodWaves = new ArrayList<Snowball>();
	public static ArrayList<UUID> bloodUsers = new ArrayList<UUID>();

	public static ArrayList<UUID> cloudUsers = new ArrayList<UUID>();
}
