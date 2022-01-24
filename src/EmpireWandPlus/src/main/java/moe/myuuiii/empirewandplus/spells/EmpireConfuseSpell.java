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

import moe.myuuiii.empirewandplus.Extensions;
import moe.myuuiii.empirewandplus.Spells;
import moe.myuuiii.empirewandplus.managers.ConfigManager;

public class EmpireConfuseSpell {
	//
	// Settings
	//
	private static int _confusionDuration = 250;
	private static int _blindnessDuration = 200;
	private static int _slownessDuration = 175;
	private static int _closeRange = 3;

	public static void Execute(Location loc, Player p) {
		if (!Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.EmpireConfuse), loc, p))
			return;

		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, loc, 250, 1, 1, 1, 0.1);
		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, loc, 250, 1, 1, 1, 0.1);

		p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_DEATH, 2, 0.85f);
		p.getWorld().playSound(loc, Sound.ENTITY_CREEPER_DEATH, 2, 0.85f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getEntities();
		for (final Entity en : near) {
			if (en.getLocation().distance(loc) <= _closeRange && en instanceof LivingEntity) {
				LivingEntity targetEntity = (LivingEntity) en;
				targetEntity.addPotionEffect(
						new PotionEffect(PotionEffectType.CONFUSION, _confusionDuration, 1, true, false));
				targetEntity.addPotionEffect(
						new PotionEffect(PotionEffectType.BLINDNESS, _blindnessDuration, 1, true, false));
				targetEntity.addPotionEffect(
						new PotionEffect(PotionEffectType.SLOW, _slownessDuration, 1, true, false));
			}
		}
	}
}
