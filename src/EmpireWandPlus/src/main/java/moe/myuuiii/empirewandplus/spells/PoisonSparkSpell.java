package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

import static moe.myuuiii.empirewandplus.Extensions.getNearbyEntities;
import static moe.myuuiii.empirewandplus.generators.FireworkGenerator.getFirework;

public class PoisonSparkSpell {
	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static double _damage = 4.5;
	private static int _poisonDuration = 150;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		launchSpellFirework(loc, p);

		p.getWorld().playSound(loc, Sound.ENTITY_ZOMBIE_INFECT, 5, 0.85f);
		p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_LEAVES_BREAK, 5, 0.85f);

		final List<Entity> near = getNearbyEntities(_closeRange, loc);
		for (final Entity en : near) {
			if (!(en instanceof LivingEntity targetEntity))
				return;

			targetEntity.damage(_damage);
			targetEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, _poisonDuration, 1, true, false));

		}
	}

	private static void launchSpellFirework(Location loc, Player p) {
		Firework fw = getFirework(p, loc);
		FireworkMeta fwMeta = fw.getFireworkMeta();
		fwMeta.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(0,255,0))
				.withFade(Color.fromRGB(0,2,0))
				.with(FireworkEffect.Type.BURST)
				.withFlicker()
				.build()
		);
		fwMeta.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(0,0,0))
				.withFade(Color.fromRGB(0,0,0))
				.with(FireworkEffect.Type.BURST)
				.build()
		);
		fw.setFireworkMeta(fwMeta);
		fw.detonate();
	}
}
