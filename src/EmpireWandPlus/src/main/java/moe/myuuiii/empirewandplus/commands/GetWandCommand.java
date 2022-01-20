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

public class GetWandCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length >= 1) {
				if (Data.wandTypes.contains(args[0].toLowerCase())) {

					ItemStack wand;
					ItemMeta wandMeta;

					switch (args[0].toLowerCase()) {
						case "blood":
							if (player.hasPermission("bloodwand.get")) {
								wand = new ItemStack(Material.BONE, 1);
								wandMeta = wand.getItemMeta();
								wandMeta.setDisplayName(Data.bloodWandName);
								wand.setItemMeta(wandMeta);
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage(ChatColor.RED + "Your inventory is full!");
									return false;
								}
								player.getInventory().addItem(new ItemStack[] { wand });

								player.sendMessage(
										Data.prefix + ChatColor.GRAY + "You have been given a " + ChatColor.RED
												+ "BLOOD" + ChatColor.GRAY + " wand");
							} else {
								player.sendMessage(Data.prefix + ChatColor.RED + "You're not allowed to use that!");
							}
							break;
						case "empire":
							if (player.hasPermission("empirewand.get")) {
								wand = new ItemStack(Material.BLAZE_ROD, 1);
								wandMeta = wand.getItemMeta();
								wandMeta.setDisplayName(Data.empireWandName);
								wand.setItemMeta(wandMeta);
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage(ChatColor.RED + "Your inventory is full!");
									return false;
								}
								player.getInventory().addItem(new ItemStack[] { wand });

								player.sendMessage(
										Data.prefix + ChatColor.GRAY + "You have been given an " + ChatColor.GOLD
												+ "EMPIRE" + ChatColor.GRAY + " wand");
							} else {
								player.sendMessage(Data.prefix + ChatColor.RED + "You're not allowed to use that!");
							}
							break;
						case "scythe":
							if (player.hasPermission("scythe.get")) {
								wand = new ItemStack(Material.IRON_HOE, 1);
								wandMeta = wand.getItemMeta();
								wandMeta.setDisplayName(Data.scytheWandName);
								wand.setItemMeta(wandMeta);
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage(ChatColor.RED + "Your inventory is full");
									return false;
								}

								player.getInventory().addItem(new ItemStack[] { wand });
								player.sendMessage(Data.prefix + ChatColor.GRAY + "You have been give a "
										+ ChatColor.GREEN + "POISON SCYTHE" + ChatColor.GRAY + " wand");
							} else {
								player.sendMessage(Data.prefix + ChatColor.RED + "You're not allowed to use that!");
							}
							break;
						case "celestial":
							player.sendMessage(Data.prefix + ChatColor.GOLD + Data.wandNotImplementedYet);

							if (player.hasPermission("celestial.get")) {
								wand = new ItemStack(Material.AMETHYST_SHARD, 1);
								wandMeta = wand.getItemMeta();
								wandMeta.setDisplayName(Data.celestialWandName);
								wand.setItemMeta(wandMeta);
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage(ChatColor.RED + "Your inventory is full");
									return false;
								}
								player.getInventory().addItem(new ItemStack[] { wand });
								player.sendMessage(Data.prefix + ChatColor.GRAY + "You have been given a "
										+ ChatColor.LIGHT_PURPLE + "CELESTIAL" + ChatColor.GRAY + " wand");
							} else {
								player.sendMessage(Data.prefix + ChatColor.RED + "You're not allowed to use that!");
							}
							break;
						case "hell":
							player.sendMessage(Data.prefix + ChatColor.GOLD + Data.wandNotImplementedYet);
							if (player.hasPermission("hell.get")) {

							}
							break;
					}
				} else {
					player.sendMessage(Data.prefix + ChatColor.RED + "This wand does not exist!");
				}
			} else {
				player.sendMessage(Data.prefix + "You have to provide the wand type");
			}
		}

		return false;
	}

}
