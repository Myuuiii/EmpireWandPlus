package mc.subsilence.empirewandplus.listeners.wandinteraction;

import mc.subsilence.empirewandplus.Data;
import mc.subsilence.empirewandplus.spells.SparkSpell;
import mc.subsilence.empirewandplus.spells.CloudSpell;
import mc.subsilence.empirewandplus.spells.EmpireCometSpell;
import mc.subsilence.empirewandplus.spells.EmpireSparkSpell;
import mc.subsilence.empirewandplus.spells.FireballSpell;
import mc.subsilence.empirewandplus.spells.LaunchSpell;
import mc.subsilence.empirewandplus.spells.LightningSpell;
import mc.subsilence.empirewandplus.spells.PoisonSparkSpell;
import mc.subsilence.empirewandplus.spells.PoisonWaveSpell;

import java.util.HashSet;
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
				if (wand.getItemMeta().getDisplayName().equals(Data.empireWandName)) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Spark)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Spark");
					wand.setItemMeta(meta);
					return;
				}

				//
				// Spell cycle
				//
				if (wand.getItemMeta().getDisplayName().contains("Spark")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Lightning)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Lightning");
					wand.setItemMeta(meta);
					return;
				}
				if (wand.getItemMeta().getDisplayName().contains("Lightning")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Launch)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Launch");
					wand.setItemMeta(meta);
					return;
				}
				if (wand.getItemMeta().getDisplayName().contains("Launch")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Fireball)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Fireball");
					wand.setItemMeta(meta);
					return;
				}
				if (wand.getItemMeta().getDisplayName().contains("Fireball")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Empire Comet)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Empire Comet");
					wand.setItemMeta(meta);
					return;
				}
				if (wand.getItemMeta().getDisplayName().contains("Empire Comet")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Cloud)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Cloud");
					wand.setItemMeta(meta);
					return;
				}
				if (wand.getItemMeta().getDisplayName().contains("Cloud")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Poison Wave)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Poison Wave");
					wand.setItemMeta(meta);
					return;
				}
				if (wand.getItemMeta().getDisplayName().contains("Poison Wave")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Poison Spark)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Poison Spark");
					wand.setItemMeta(meta);
					return;
				}
				if (wand.getItemMeta().getDisplayName().contains("Poison Spark")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Empire Spark)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Empire Spark");
					wand.setItemMeta(meta);
					return;
				}

				//
				// Reset cycle
				//
				if (wand.getItemMeta().getDisplayName().contains("Empire Spark")) {
					meta.setDisplayName(Data.empireWandName + ChatColor.GRAY + " (Spark)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Spark");
					wand.setItemMeta(meta);
					return;
				}
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
				if (wand.getItemMeta().getDisplayName().contains("Spark"))
					SparkSpell.Execute(loc, p);

				if (wand.getItemMeta().getDisplayName().contains("Lightning"))
					LightningSpell.Execute(loc, p);

				if (wand.getItemMeta().getDisplayName().contains("Launch"))
					LaunchSpell.Execute(loc, p);

				if (wand.getItemMeta().getDisplayName().contains("Fireball"))
					FireballSpell.Execute(loc, p);

				if (wand.getItemMeta().getDisplayName().contains("Empire Comet"))
					EmpireCometSpell.Execute(loc, p);

				if (wand.getItemMeta().getDisplayName().contains("Cloud"))
					CloudSpell.Execute(loc, p);

				if (wand.getItemMeta().getDisplayName().contains("Poison Wave"))
					PoisonWaveSpell.Execute(loc, p);

				if (wand.getItemMeta().getDisplayName().contains("Poison Spark"))
					PoisonSparkSpell.Execute(loc, p);

				if (wand.getItemMeta().getDisplayName().contains("Empire Spark"))
					EmpireSparkSpell.Execute(loc, p);
			}
		}
	}
}
