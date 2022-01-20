package moe.myuuiii.empirewandplus.listeners.wandinteraction;

import moe.myuuiii.empirewandplus.Data;
import moe.myuuiii.empirewandplus.spells.CloudSpell;
import moe.myuuiii.empirewandplus.spells.EmpireCometSpell;
import moe.myuuiii.empirewandplus.spells.EmpireSparkSpell;
import moe.myuuiii.empirewandplus.spells.FireballSpell;
import moe.myuuiii.empirewandplus.spells.LaunchSpell;
import moe.myuuiii.empirewandplus.spells.LightningSpell;
import moe.myuuiii.empirewandplus.spells.PoisonSparkSpell;
import moe.myuuiii.empirewandplus.spells.PoisonWaveSpell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.event.block.Action;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EmpireWandInteraction {
	public static void Handle(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		//
		//
		//
		// Empire wand
		//
		//
		//
		if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().hasItemMeta()
				&& p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory()
						.getItemInMainHand().getItemMeta().getDisplayName().startsWith(Data.empireWandName)) {
			if (!p.hasPermission("empirewand.use")) {
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

					switch (wand.getItemMeta().getLore().get(0)) {
						case "Launch":
							loreItems.set(0, "Fireball");
							break;
						case "Fireball":
							loreItems.set(0, "Empire Comet");
							break;
						case "Empire Comet":
							loreItems.set(0, "Cloud");
							break;
						case "Cloud":
							loreItems.set(0, "Poison Wave");
							break;
						case "Poison Wave":
							loreItems.set(0, "Poison Spark");
							break;
						case "Poison Spark":
							loreItems.set(0, "Empire Spark");
							break;

						// reset
						case "Empire Spark":
						default:
							loreItems.set(0, "Launch");
							break;
					}
				} else {
					loreItems.add("Lightning");
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

				final Location loc = p.getTargetBlock((HashSet) null, 200).getLocation();

				//
				// Spell execution
				//
				switch (wand.getItemMeta().getLore().get(0)) {
					case "Lightning":
						LightningSpell.Execute(loc, p);
						break;
					case "Launch":
						LaunchSpell.Execute(loc, p);
						break;
					case "Fireball":
						FireballSpell.Execute(loc, p);
						break;
					case "Empire Comet":
						EmpireCometSpell.Execute(loc, p);
						break;
					case "Cloud":
						CloudSpell.Execute(loc, p);
						break;
					case "Poison Wave":
						PoisonWaveSpell.Execute(loc, p);
						break;
					case "Poison Spark":
						PoisonSparkSpell.Execute(loc, p);
						break;
					case "Empire Spark":
						EmpireSparkSpell.Execute(loc, p);
						break;
				}
			}
		}
	}
}
