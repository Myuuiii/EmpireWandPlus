package moe.myuuiii.empirewandplus.spells;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

import static moe.myuuiii.empirewandplus.generators.FireworkGenerator.getFirework;

public class BloodSparkSpell {
	//
	// Settings
	//
	private static double _closeRange = 3.0;
	private static double _damage = 7.5;
	private static int _witherDuration = 100;

	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		launchSpellFirework(loc, p);

		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc, 100, 0, 0, 0, 0.1);

		p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_BREAK, 5, 0.65f);
		p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_LEAVES_BREAK, 5, 0.65f);

		final List<Entity> near = (List<Entity>) loc.getWorld().getNearbyEntities(loc, _closeRange, _closeRange,
				_closeRange);
		for (final Entity en : near) {
			if (!(en instanceof LivingEntity targetEntity))
				return;

			targetEntity.damage(_damage);
			targetEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, _witherDuration, 1, true, false));
		}
	}

	private static void launchSpellFirework(Location loc, Player p) {
		Firework fw = getFirework(p, loc);
		FireworkMeta fwMeta = fw.getFireworkMeta();
		fwMeta.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255,0,0))
				.withFade(Color.fromRGB(150,0,0))
				.with(FireworkEffect.Type.BURST)
				.withFlicker()
				.build()
		);
		fw.setFireworkMeta(fwMeta);
		fw.detonate();
	}
}
