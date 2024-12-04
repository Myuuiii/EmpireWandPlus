package com.myuuiii.empirewandplus;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class DynamicCircleGenerator {
    /**
     * Generates a circle of blocks with the specified radius around the given center location.
     *
     * @param center The center location of the circle.
     * @param radius The radius of the circle.
     * @return A list of blocks that form the circle.
     */
    public List<Block> GenerateCircle(Location center, int radius) {
        List<Block> circleBlocks = new ArrayList<>();
        int cx = center.getBlockX();
        int cz = center.getBlockZ();
        int y = center.getBlockY();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int distanceSquared = x * x + z * z;
                if (distanceSquared <= radius * radius && distanceSquared > (radius - 1) * (radius - 1)) {
                    circleBlocks.add(center.getWorld().getBlockAt(cx + x, y, cz + z));
                }
            }
        }
        return circleBlocks;
    }
}
