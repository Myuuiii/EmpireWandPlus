package com.myuuiii.empirewandplus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WandCommandCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0],
                    Arrays.asList("Empire", "Blood", "Elementos"), new ArrayList<>());
        }
        return null;
    }
}