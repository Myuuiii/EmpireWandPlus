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

public class PoisonSparkSpell {
	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static double _damage = 4.5;
	private static int _poisonDuration = 150;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 100, 0, 0, 0, 0.1);
		p.getWorld().spawnParticle(Particle.REDSTONE, loc, 75, 0.5, 0.5, 0.5, 3,
				new DustOptions(Color.fromRGB(0, 255, 0), 2));

		p.getWorld().playSound(loc, Sound.ENTITY_ZOMBIE_INFECT, 5, 0.85f);
		p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_LEAVES_BREAK, 5, 0.85f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getNearbyEntities(loc, _closeRange, _closeRange,
				_closeRange);
		for (final Entity en : near) {
			if (!(en instanceof LivingEntity targetEntity))
				return;

			targetEntity.damage(_damage);
			targetEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, _poisonDuration, 1, true, false));

		}
	}
}
