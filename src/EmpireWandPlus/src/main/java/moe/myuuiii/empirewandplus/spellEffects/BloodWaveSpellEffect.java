package moe.myuuiii.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;

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
					s.getWorld().spawnParticle(Particle.REDSTONE, s.getLocation(), 75, 0.5, 0.5, 0.5, 1,
							new DustOptions(Color.fromRGB(255, 0, 0), 2));
					s.getWorld().spawnParticle(Particle.SMOKE_LARGE, s.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);

					s.getWorld().playSound(s.getLocation(), Sound.BLOCK_AZALEA_BREAK, 1, 0.65f);
					s.getWorld().playSound(s.getLocation(), Sound.BLOCK_AZALEA_LEAVES_BREAK, 1, 0.65f);
					s.getWorld().playSound(s.getLocation(), Sound.ENTITY_SPIDER_HURT, 1, 0.10f);

					final List<Entity> near = (List<Entity>) s.getWorld().getNearbyEntities(s.getLocation(),
							_closeRange, _closeRange, _closeRange);
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
}
