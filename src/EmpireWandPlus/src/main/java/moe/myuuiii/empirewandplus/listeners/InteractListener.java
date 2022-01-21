package moe.myuuiii.empirewandplus.listeners;

import org.bukkit.event.EventHandler;

import moe.myuuiii.empirewandplus.listeners.wandinteraction.BloodWandInteraction;
import moe.myuuiii.empirewandplus.listeners.wandinteraction.CelestialWandInteraction;
import moe.myuuiii.empirewandplus.listeners.wandinteraction.EmpireWandInteraction;
import moe.myuuiii.empirewandplus.listeners.wandinteraction.HellWandInteraction;
import moe.myuuiii.empirewandplus.listeners.wandinteraction.ScytheWandInteraction;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import org.bukkit.event.Listener;

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
