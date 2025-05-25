package com.myuuiii.empirewandplus.Commands;

import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WandCommandCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            // Get identifiers from all wands in the hashmap
            List<String> identifiers = EmpireWandPlus.wandHashMap.values().stream()
                    .map(Wand::getIdentifier)
                    .collect(Collectors.toList());
            
            return StringUtil.copyPartialMatches(args[0], identifiers, new ArrayList<>());
        }
        return null;
    }
}