package moe.myuuiii.empirewandplus.listeners.wandinteraction;

import moe.myuuiii.empirewandplus.Data;
import moe.myuuiii.empirewandplus.WandSpellLists;
import moe.myuuiii.empirewandplus.handlers.SpellHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.event.block.Action;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HellWandInteraction {
	public static void Handle(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		//
		//
		//
		// Blood wand
		//
		//
		//
		if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().hasItemMeta()
				&& p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()
				&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith(Data.hellWandName)) {
			if (!p.hasPermission("hell.use")) {
				p.sendMessage(ChatColor.RED + "You're not allowed to use that!");
				return;
			}
			final ItemStack wand = p.getInventory().getItemInMainHand();

			//
			//
			//
			// Right Click Handling
			//
			//
			//
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				e.setCancelled(true);

				final ItemMeta meta = wand.getItemMeta();
				p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 250, 0.5, 0.0, 0.5, 0.05);

				//
				// Initial Spell Configuration
				//
				List<String> loreItems = new ArrayList<>();
				if (wand.getItemMeta().hasLore()) {
					loreItems = wand.getItemMeta().getLore();

					Integer currentSpellIndex = WandSpellLists.HellWandSpells
							.indexOf(wand.getItemMeta().getLore().get(0));
					Integer nextSpellIndex = currentSpellIndex + 1;

					if (nextSpellIndex >= WandSpellLists.HellWandSpells.size()) {
						nextSpellIndex = 0;
					}

					loreItems.set(0, WandSpellLists.HellWandSpells.get(nextSpellIndex));
				} else {
					loreItems.add(WandSpellLists.HellWandSpells.get(0));
				}

				meta.setLore(loreItems);
				wand.setItemMeta(meta);
				p.sendMessage(Data.prefix + Data.currentSpellMessage + wand.getItemMeta().getLore().get(0));
				return;
			}

			//
			//
			//
			// LEFT CLICK HANDLING
			//
			//
			//
			if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
				e.setCancelled(true);
				final Location loc = p.getTargetBlock((HashSet<Material>) null, 200).getLocation();

				//
				// Spell execution
				//
				SpellHandler.HandleSpellByName(wand.getItemMeta().getLore().get(0), loc, p);
			}
		}
	}
}