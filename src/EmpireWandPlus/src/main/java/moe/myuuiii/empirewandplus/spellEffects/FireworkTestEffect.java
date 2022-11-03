package moe.myuuiii.empirewandplus.spellEffects;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static moe.myuuiii.empirewandplus.generators.FireworkGenerator.getFirework;

public class FireworkTestEffect {

	//
	// Settings
	//
	private static double _closeRange = 10;
	private static double _damage = 6;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.fireworkTest.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						//s.getWorld().createExplosion(s.getLocation(), 10, false);

						final List<Entity> near = (List<Entity>) s.getWorld().getNearbyEntities(s.getLocation(),
								_closeRange, _closeRange, _closeRange);
						for (final Entity en : near) {
							if (en instanceof LivingEntity targetEntity) {
								targetEntity.damage(_damage);
							}
						}
						this.cancel();
					}

					// Executed while the entity is alive
					Firework fw = getFirework(s);
					FireworkMeta meta = fw.getFireworkMeta();
					meta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(10,10,10)).build());
					meta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(255,0,150)).with(FireworkEffect.Type.BALL).build());
					fw.setFireworkMeta(meta);
					fw.detonate();
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);
	}





}
