package moe.myuuiii.empirewandplus.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class GetWandCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			return StringUtil.copyPartialMatches(args[0],
					Arrays.asList(new String[] { "Empire", "Blood", "Scythe", "Celestial", "Aqua", "Hell" }),
					new ArrayList<>());
		}
		return null;
	}

}
