package mc.subsilence.empirewandplus.listeners.wandinteraction;

import mc.subsilence.empirewandplus.Data;
import mc.subsilence.empirewandplus.spells.SparkSpell;
import mc.subsilence.empirewandplus.spells.BloodSparkSpell;
import mc.subsilence.empirewandplus.spells.BloodWaveSpell;
import mc.subsilence.empirewandplus.spells.CloudSpell;
import mc.subsilence.empirewandplus.spells.EmpireCometSpell;
import mc.subsilence.empirewandplus.spells.FireballSpell;
import mc.subsilence.empirewandplus.spells.LaunchSpell;
import mc.subsilence.empirewandplus.spells.LightningSpell;
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

public class BloodWandInteraction {
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
				&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith(Data.bloodWandName)) {
			if (!p.hasPermission("bloodwand.use")) {
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
				if (wand.getItemMeta().getDisplayName().equals(Data.bloodWandName)) {
					meta.setDisplayName(Data.bloodWandName + ChatColor.GRAY + " (Blood Spark)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Blood Spark");
					wand.setItemMeta(meta);
					return;
				}

				//
				// Spell cycle
				//
				if (wand.getItemMeta().getDisplayName().contains("Blood Spark")) {
					meta.setDisplayName(Data.bloodWandName + ChatColor.GRAY + " (Blood Wave)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Blood Wave");
					wand.setItemMeta(meta);
					return;
				}

				//
				// Reset cycle
				//
				if (wand.getItemMeta().getDisplayName().contains("Blood Wave")) {
					meta.setDisplayName(Data.bloodWandName + ChatColor.GRAY + " (Blood Spark)");
					p.sendMessage(Data.prefix + Data.currentSpellMessage + "Blood Spark");
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
				if (wand.getItemMeta().getDisplayName().contains("Blood Spark")) {
					BloodSparkSpell.Execute(loc, p);
				}
				if (wand.getItemMeta().getDisplayName().contains("Blood Wave")) {
					BloodWaveSpell.Execute(loc, p);
				}
			}
		}
	}
}
