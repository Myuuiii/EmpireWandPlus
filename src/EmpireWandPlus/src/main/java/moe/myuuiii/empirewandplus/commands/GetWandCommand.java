package moe.myuuiii.empirewandplus.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import moe.myuuiii.empirewandplus.Data;
import net.md_5.bungee.api.ChatColor;

import static moe.myuuiii.empirewandplus.Extensions.colorText;

public class GetWandCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player player))
			return false;

		if (args.length != 1) {
			player.sendMessage(Data.prefix + colorText("&cUsage: /wand [wand type]"));
			return false;
		}

		if (!Data.wandTypes.contains(args[0].toLowerCase())) {
			player.sendMessage(Data.prefix + colorText("&cInvalid wand type!"));
			return false;
		}

		if (player.getInventory().firstEmpty() == -1) {
			player.sendMessage(Data.prefix + colorText("Your inventory is full!"));
			return false;
		}

		ItemStack wand;
		ItemMeta wandMeta;

		switch (args[0].toLowerCase()) {
			case "blood" -> {

				if (!player.hasPermission("bloodwand.get")) {
					player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
					return false;
				}

				wand = new ItemStack(Material.BONE, 1);
				wandMeta = wand.getItemMeta();
				wandMeta.setDisplayName(Data.bloodWandName);
				wand.setItemMeta(wandMeta);
				player.getInventory().addItem(wand);
				player.sendMessage(Data.prefix + colorText("&7You have been given a &cBLOOD &7wand!"));
			}
			case "empire" -> {

				if (!player.hasPermission("empirewand.get")) {
					player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
					return false;
				}

				wand = new ItemStack(Material.BLAZE_ROD, 1);
				wandMeta = wand.getItemMeta();
				wandMeta.setDisplayName(Data.empireWandName);
				wand.setItemMeta(wandMeta);
				player.getInventory().addItem(wand);
				player.sendMessage(Data.prefix + colorText("&7You have been given an &6EMPIRE &7wand!"));
			}
			case "scythe" -> {

				if (!player.hasPermission("scythewand.get")) {
					player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
					return false;
				}

				wand = new ItemStack(Material.IRON_HOE, 1);
				wandMeta = wand.getItemMeta();
				wandMeta.setDisplayName(Data.scytheWandName);
				wand.setItemMeta(wandMeta);
				player.getInventory().addItem(wand);
				player.sendMessage(Data.prefix + colorText("&7You have been given a &aPOISON SCYTHE &7wand!"));
			}
			case "celestial" -> {

				if (!player.hasPermission("celestialwand.get")) {
					player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
					return false;
				}

				wand = new ItemStack(Material.AMETHYST_SHARD, 1);
				wandMeta = wand.getItemMeta();
				wandMeta.setDisplayName(Data.celestialWandName);
				wand.setItemMeta(wandMeta);
				player.getInventory().addItem(wand);
				player.sendMessage(Data.prefix + colorText("&7You have been given a &dCELESTIAL &7wand!"));
			}
			case "hell" -> {

				if (!player.hasPermission("hellwand.get")) {
					player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
					return false;
				}

				wand = new ItemStack(Material.BLAZE_POWDER, 1);
				wandMeta = wand.getItemMeta();
				wandMeta.setDisplayName(Data.hellWandName);
				wand.setItemMeta(wandMeta);
				player.getInventory().addItem(wand);
				player.sendMessage(Data.prefix + colorText("&7You have been given a &cHELL &7wand!"));
			}
			case "dreambender" -> {
				if (!player.hasPermission("dreambenderwand.get")) {
					player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
					return false;
				}

				wand = new ItemStack(Material.BAMBOO, 1);
				wandMeta = wand.getItemMeta();
				wandMeta.setDisplayName(Data.dreambenderWandName);
				wand.setItemMeta(wandMeta);
				player.getInventory().addItem(wand);
				player.sendMessage(Data.prefix + colorText("&7You have been given a &eDREAMBENDER &7wand!"));
			}
		}

		return false;
	}

}
