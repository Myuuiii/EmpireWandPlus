package moe.myuuiii.empirewandplus.listeners;

import moe.myuuiii.empirewandplus.listeners.wandinteraction.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

public class InteractListener implements Listener {
	HashMap<String, Long> wandCooldown;
	long cooldownTime;

	public InteractListener() {
		this.wandCooldown = new HashMap<String, Long>();
	}

	@EventHandler
	public void onInteract(final PlayerInteractEvent e) {
		if (e.getHand().equals(EquipmentSlot.HAND)) {
			EmpireWandInteraction.Handle(e);
			BloodWandInteraction.Handle(e);
			ScytheWandInteraction.Handle(e);
			CelestialWandInteraction.Handle(e);
			HellWandInteraction.Handle(e);
		}
	}
}
