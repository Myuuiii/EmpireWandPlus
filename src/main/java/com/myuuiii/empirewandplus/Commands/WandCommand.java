package com.myuuiii.empirewandplus.Commands;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Wands.BloodWand;
import com.myuuiii.empirewandplus.Wands.EmpireWand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.myuuiii.empirewandplus.Extensions.colorText;

public class WandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player))
            return false;

        if (args.length != 1) {
            player.sendMessage(EmpireWandPlus.Prefix + colorText("&cUsage: /wand [wand type]"));
            return false;
        }

        if (!EmpireWandPlus.wandHashMap.containsKey(args[0].toLowerCase())) {
            player.sendMessage(EmpireWandPlus.Prefix + colorText("&cInvalid wand type!"));
            return false;
        }

        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(EmpireWandPlus.Prefix + colorText("&cYour inventory is full!"));
            return false;
        }

        ItemStack wand;
        ItemMeta wandMeta;

        switch (args[0].toLowerCase()) {
            case "blood" -> {
                BloodWand bWand = (BloodWand) EmpireWandPlus.wandHashMap.get(args[0].toLowerCase());

                if (!player.hasPermission(bWand.getObtainPermissionName())) {
                    player.sendMessage(EmpireWandPlus.Prefix + colorText("&cYou're not allowed to use that!"));
                    return false;
                }

                player.getInventory().addItem(bWand.getItem());
                player.sendMessage(EmpireWandPlus.Prefix + colorText("&7You have been given a " + bWand.getDisplayName()));
            }
            case "empire" -> {
                EmpireWand eWand = (EmpireWand) EmpireWandPlus.wandHashMap.get(args[0].toLowerCase());

                if (!player.hasPermission(eWand.getObtainPermissionName())) {
                    player.sendMessage(EmpireWandPlus.Prefix + colorText("&cYou're not allowed to use that!"));
                    return false;
                }

                player.getInventory().addItem(eWand.getItem());
                player.sendMessage(EmpireWandPlus.Prefix + colorText("&7You have been given an " + eWand.getDisplayName()));
            }
//            case "scythe" -> {
//
//                if (!player.hasPermission("scythewand.get")) {
//                    player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
//                    return false;
//                }
//
//                wand = new ItemStack(Material.IRON_HOE, 1);
//                wandMeta = wand.getItemMeta();
//                wandMeta.setDisplayName(Data.scytheWandName);
//                wand.setItemMeta(wandMeta);
//                player.getInventory().addItem(wand);
//                player.sendMessage(Data.prefix + colorText("&7You have been given a &aPOISON SCYTHE &7wand!"));
//            }
//            case "celestial" -> {
//
//                if (!player.hasPermission("celestialwand.get")) {
//                    player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
//                    return false;
//                }
//
//                wand = new ItemStack(Material.AMETHYST_SHARD, 1);
//                wandMeta = wand.getItemMeta();
//                wandMeta.setDisplayName(Data.celestialWandName);
//                wand.setItemMeta(wandMeta);
//                player.getInventory().addItem(wand);
//                player.sendMessage(Data.prefix + colorText("&7You have been given a &dCELESTIAL &7wand!"));
//            }
//            case "hell" -> {
//
//                if (!player.hasPermission("hellwand.get")) {
//                    player.sendMessage(Data.prefix + colorText("&cYou're not allowed to use that!"));
//                    return false;
//                }
//
//                wand = new ItemStack(Material.BLAZE_POWDER, 1);
//                wandMeta = wand.getItemMeta();
//                wandMeta.setDisplayName(Data.hellWandName);
//                wand.setItemMeta(wandMeta);
//                player.getInventory().addItem(wand);
//                player.sendMessage(Data.prefix + colorText("&7You have been given a &cHELL &7wand!"));
//            }
        }

        return false;
    }
}
