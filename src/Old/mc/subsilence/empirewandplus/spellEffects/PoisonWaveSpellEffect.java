package mc.subsilence.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import mc.subsilence.empirewandplus.App;
import mc.subsilence.empirewandplus.Data;
import net.minecraft.core.particles.ParticleParam;

public class PoisonWaveSpellEffect {
	//
	// Settings
	//
	private static double _closeRange = 4;
	private static int _poisonDuration = 100;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.poisonWaves.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.REDSTONE, s.getLocation(), 75, 0.5, 0.5, 0.5, 1,
							new DustOptions(Color.fromRGB(10, 175, 0), 2));
					s.getWorld().spawnParticle(Particle.SMOKE_LARGE, s.getLocation(), 75, 0.5, 0.5, 0.5, 0.05);
					final List<Entity> near = (List<Entity>) s.getLocation().getWorld().getEntities();
					for (final Entity en : near) {
						if (en.getLocation().distance(s.getLocation()) <= _closeRange && en instanceof Damageable) {
							if (en instanceof Player) {
								Player p = (Player) en;
								if (!Data.poisonUsers.contains(p.getUniqueId())) {
									p.addPotionEffect(
											new PotionEffect(PotionEffectType.POISON, _poisonDuration, 1, true, false));
								}
							}
						}
					}
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);
	}
}
