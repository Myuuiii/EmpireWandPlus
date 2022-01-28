package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CelestialStunSpell {
	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static int _slowDuration = 100;
	public static int _blindnessDuration = 100;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 100, 0.5, 1, 0.5, 0.5);
		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, loc, 100, 0.5, 1, 0.5, 0.1);
		p.getWorld().spawnParticle(Particle.SNOWFLAKE, loc.add(0, 2, 0), 100, 0.5, 1, 0.5, 0);

		p.getWorld().playSound(loc, Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 2, 0.65f);
		p.getWorld().playSound(loc, Sound.ITEM_TOTEM_USE, 2, 0.65f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getNearbyEntities(loc, _closeRange, _closeRange,
				_closeRange);
		for (final Entity en : near) {
			if (en instanceof Entity) {
				if (en instanceof LivingEntity) {
					((LivingEntity) en)
							.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, _slowDuration, 255, true, false));
					((LivingEntity) en).addPotionEffect(
							new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));
				}
			}
		}
	}
}
