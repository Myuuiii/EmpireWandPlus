package mc.subsilence.empirewandplus.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import mc.subsilence.empirewandplus.Data;
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
						wand = new ItemStack(Material.BONE, 1);
						wandMeta = wand.getItemMeta();
						wandMeta.setDisplayName(Data.bloodWandName);
						wand.setItemMeta(wandMeta);
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage(ChatColor.RED + "Your inventory is full!");
							return false;
						}
						player.getInventory().addItem(new ItemStack[] { wand });

						player.sendMessage(Data.prefix + ChatColor.GRAY + "You have been given a " + ChatColor.RED
								+ "BLOOD" + ChatColor.GRAY + " wand");
						break;
					case "empire":
						wand = new ItemStack(Material.BLAZE_ROD, 1);
						wandMeta = wand.getItemMeta();
						wandMeta.setDisplayName(Data.empireWandName);
						wand.setItemMeta(wandMeta);
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage(ChatColor.RED + "Your inventory is full!");
							return false;
						}
						player.getInventory().addItem(new ItemStack[] { wand });

						player.sendMessage(Data.prefix + ChatColor.GRAY + "You have been given an " + ChatColor.GOLD
								+ "EMPIRE" + ChatColor.GRAY + " wand");
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
