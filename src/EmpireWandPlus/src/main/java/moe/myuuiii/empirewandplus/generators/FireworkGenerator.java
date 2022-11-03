package moe.myuuiii.empirewandplus.generators;

import moe.myuuiii.empirewandplus.App;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class FireworkGenerator {
    public static Firework getFirework(Snowball s) {
        Firework fw = s.getWorld().spawn(s.getLocation(), Firework.class);
        fw.setMetadata("nodamage", new FixedMetadataValue(App._app, true));
        return fw;
    }
}
