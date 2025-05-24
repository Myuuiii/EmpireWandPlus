package com.myuuiii.empirewandplus.Commands;

import com.myuuiii.empirewandplus.EmpireWandPlus;
import com.myuuiii.empirewandplus.Managers.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length != 1) {
            player.sendMessage(EmpireWandPlus.Prefix + ConfigManager.getWandCommandMessage("usage"));
            return false;
        }

        String wandType = args[0].toLowerCase();
        if (!EmpireWandPlus.wandHashMap.containsKey(wandType)) {
            player.sendMessage(EmpireWandPlus.Prefix + ConfigManager.getWandCommandMessage("invalid-wand"));
            return false;
        }

        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(EmpireWandPlus.Prefix + ConfigManager.getWandCommandMessage("inventory-full"));
            return false;
        }

        var wand = EmpireWandPlus.wandHashMap.get(wandType);
        if (!player.hasPermission(wand.getObtainPermissionName())) {
            player.sendMessage(EmpireWandPlus.Prefix + ConfigManager.getWandCommandMessage("no-permission"));
            return false;
        }

        player.getInventory().addItem(wand.getItem());
        
        boolean startsWithVowel = switch (wandType) {
            case "empire", "elementos" -> true;
            default -> false;
        };
        
        player.sendMessage(EmpireWandPlus.Prefix + 
            ConfigManager.getWandGivenMessage(wand.getDisplayName(), startsWithVowel));

        return true;
    }
}