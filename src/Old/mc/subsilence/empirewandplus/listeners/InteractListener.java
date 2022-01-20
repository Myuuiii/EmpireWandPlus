package mc.subsilence.empirewandplus.listeners;

import org.bukkit.event.EventHandler;

import mc.subsilence.empirewandplus.listeners.wandinteraction.BloodWandInteraction;
import mc.subsilence.empirewandplus.listeners.wandinteraction.EmpireWandInteraction;

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
		}
	}
}
