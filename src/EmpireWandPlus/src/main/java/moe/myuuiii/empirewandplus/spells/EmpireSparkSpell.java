package moe.myuuiii.empirewandplus.spells;

import org.bukkit.*;
import org.bukkit.Particle.DustOptions;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static moe.myuuiii.empirewandplus.generators.FireworkGenerator.getFirework;

public class EmpireSparkSpell {

    //
    // Settings
    //
    private static double _closeRange = 3.0;
    private static double _damage = 8.0;
    private static int _blindnessDuration = 100;

    public static void Execute(Location loc, Player p) {
        loc.add(0, 1, 0);

        launchSpellFirework(loc, p);

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

    private static void launchSpellFirework(Location loc, Player p) {
        Firework fw = getFirework(p, loc);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
            .withColor(Color.fromRGB(150, 0, 150))
            .with(FireworkEffect.Type.BURST)
            .withFlicker()
            .build()
        );
        fwMeta.addEffect(FireworkEffect.builder()
            .withColor(Color.fromRGB(0, 0, 0))
            .with(FireworkEffect.Type.BURST)
            .withTrail()
            .build()
        );
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
