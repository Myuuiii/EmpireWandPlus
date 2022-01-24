package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;

import java.util.List;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import moe.myuuiii.empirewandplus.Extensions;
import moe.myuuiii.empirewandplus.Spells;
import moe.myuuiii.empirewandplus.managers.ConfigManager;

public class BloodSparkSpell {
	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static double _damage = 7.5;
	private static int _witherDuration = 100;

	public static void Execute(Location loc, Player p) {
		if (!Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.BloodSpark), loc, p))
			return;

		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 100, 0, 0, 0, 0.1);
		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc, 100, 0, 0, 0, 0.1);
		p.getWorld().spawnParticle(Particle.REDSTONE, loc, 75, 0.5, 0.5, 0.5, 3,
				new DustOptions(Color.fromRGB(255, 0, 0), 2));

		p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_BREAK, 5, 0.65f);
		p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_LEAVES_BREAK, 5, 0.65f);
		p.getWorld().playSound(loc, Sound.ENTITY_SPIDER_HURT, 5, 0.65f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getEntities();
		for (final Entity en : near) {
			if (en.getLocation().distance(loc) <= _closeRange && en instanceof LivingEntity) {
				((Damageable) en).damage(_damage);
				LivingEntity targetEntity = (LivingEntity) en;
				targetEntity.addPotionEffect(
						new PotionEffect(PotionEffectType.WITHER, _witherDuration, 1, true, false));
			}
		}
	}
}
