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

import moe.myuuiii.empirewandplus.CastRange;
import moe.myuuiii.empirewandplus.Extensions;

public class EmpireStunSpell {
	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static int _slowDuration = 100;
	public static int _blindnessDuration = 100;

	public static void Execute(Location loc, Player p) {
		if (!Extensions.CheckIfInRange(CastRange.Medium, loc, p))
			return;

		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc, 100, 0.5, 1, 0.5, 0.1);
		p.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 100, 0.5, 1, 0.5, 0.1);
		p.getWorld().spawnParticle(Particle.REDSTONE, loc, 75, 0.5, 1, 0.5, 3,
				new DustOptions(Color.fromRGB(255, 0, 233), 2));

		p.getWorld().playSound(loc, Sound.ENTITY_ENDERMAN_SCREAM, 5, 0.65f);
		p.getWorld().playSound(loc, Sound.ITEM_TOTEM_USE, 5, 0.65f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getEntities();
		for (final Entity en : near) {
			if (en.getLocation().distance(loc) <= _closeRange && en instanceof LivingEntity) {
				((LivingEntity) en)
						.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, _slowDuration, 255, true, false));
				((LivingEntity) en).addPotionEffect(
						new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));
			}
		}
	}
}
