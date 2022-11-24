package com.myuuiii.empirewandplus.Listeners;

import com.myuuiii.empirewandplus.Abstracts.Wand;
import com.myuuiii.empirewandplus.EmpireWandPlus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class InteractionListener implements Listener {
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            for (Wand wand : EmpireWandPlus.wandHashMap.values()) {
                wand.Handle(e);
            }
        }
    }
}
