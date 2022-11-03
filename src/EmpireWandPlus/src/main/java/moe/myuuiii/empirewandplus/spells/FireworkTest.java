package moe.myuuiii.empirewandplus.spells;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class FireworkTest {

    //
    // Settings
    //
    private static int _rangeMultiplication = 2;

    public static void Execute(Location loc, Player p) {
        Snowball e = p.launchProjectile(Snowball.class);

        e.setVelocity(e.getVelocity().multiply(_rangeMultiplication));
        e.setGravity(false);

        Data.fireworkTest.add(e);

        new BukkitRunnable() {
            public void run() {
                if (!e.isDead())
                    e.remove();
            }
        }.runTaskLater(App._app, 200L);
    }
}
