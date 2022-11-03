package moe.myuuiii.empirewandplus.spellEffects;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static moe.myuuiii.empirewandplus.Extensions.getNearbyEntities;
import static moe.myuuiii.empirewandplus.generators.FireworkGenerator.getFirework;

public class BloodWaveSpellEffect {

	//
	// Settings
	//
	private static double _closeRange = 4;
	private static int _witherDuration = 100;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.bloodWaves.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						this.cancel();
					}

					// Executed while the entity is alive
					launchFirework(s);

					s.getWorld().playSound(s.getLocation(), Sound.BLOCK_AZALEA_BREAK, 1, 0.65f);
					s.getWorld().playSound(s.getLocation(), Sound.BLOCK_AZALEA_LEAVES_BREAK, 1, 0.65f);

					final List<Entity> near = getNearbyEntities(_closeRange, s);
					for (final Entity en : near) {
						if (!(en instanceof LivingEntity targetEntity))
							return;

						if (targetEntity instanceof Player player) {
							if (Data.bloodUsers.contains(player.getUniqueId()))
								continue;
						}

						targetEntity.addPotionEffect(
								new PotionEffect(PotionEffectType.WITHER, _witherDuration, 1, true, false));
					}
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);
	}

	private static void launchFirework(Snowball s) {
		Firework fw = getFirework(s);
		FireworkMeta fwMeta = fw.getFireworkMeta();
		fwMeta.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255,0,0))
				.withFade(Color.BLACK)
				.with(FireworkEffect.Type.BALL)
				.build()
		);
		fwMeta.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(75,0,0))
				.withFade(Color.fromRGB(255,0,0))
				.with(FireworkEffect.Type.BALL)
				.build()
		);
		fw.setFireworkMeta(fwMeta);
		fw.detonate();
	}
}
