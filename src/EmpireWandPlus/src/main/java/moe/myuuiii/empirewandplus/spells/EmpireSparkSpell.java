package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EmpireSparkSpell {

	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static double _damage = 8.0;
	private static int _blindnessDuration = 100;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc, 100, 0, 0, 0, 0.1);
		p.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 100, 0, 0, 0, 0.1);
		p.getWorld().spawnParticle(Particle.REDSTONE, loc, 75, 0.5, 0.5, 0.5, 3,
				new DustOptions(Color.fromRGB(255, 0, 233), 2));

		p.getWorld().playSound(loc, Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 5, 0.85f);
		p.getWorld().playSound(loc, Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 5, 0.65f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getNearbyEntities(loc, _closeRange, _closeRange,
				_closeRange);
		for (final Entity en : near) {

			if (!(en instanceof LivingEntity targetEntity))
				return;

			targetEntity.damage(_damage);
			targetEntity
					.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));

		}
	}
}
