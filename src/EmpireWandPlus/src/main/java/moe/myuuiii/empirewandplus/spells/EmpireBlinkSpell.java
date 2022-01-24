package moe.myuuiii.empirewandplus.spells;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.Extensions;
import moe.myuuiii.empirewandplus.Spells;
import moe.myuuiii.empirewandplus.managers.ConfigManager;

public class EmpireBlinkSpell {
	public static void Execute(Location loc, Player p) {
		loc.add(0, 1, 0);

		p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, p.getLocation(), 200, 0.5, 0.5, 0.5, 0.2);
		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 100, 0.5, 0.5, 0.5, 0.1);
		p.getWorld().playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 0.90f);
		p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_LEAVES_BREAK, 5, 0.85f);

		loc.setPitch(p.getLocation().getPitch());
		loc.setYaw(p.getLocation().getYaw());
		p.teleport(loc);

		p.getWorld().spawnParticle(Particle.PORTAL, loc, 200, 0.5, 0.5, 0.5, 0.2);
		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 100, 0.5, 0.5, 0.5, 0.1);
		p.getWorld().playSound(loc, Sound.BLOCK_AZALEA_LEAVES_BREAK, 5, 0.85f);
	}
}
