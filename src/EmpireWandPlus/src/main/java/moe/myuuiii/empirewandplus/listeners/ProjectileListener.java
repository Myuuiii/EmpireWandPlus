package moe.myuuiii.empirewandplus.listeners;

import moe.myuuiii.empirewandplus.spellEffects.*;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileListener implements Listener {

	@EventHandler
	public void onProjectileLaunch(final ProjectileLaunchEvent e) {

		if (e.getEntity() instanceof Snowball) {
			final Snowball s = (Snowball) e.getEntity();
			EmpireCometSpellEffect.Execute(s);
			FireballSpellEffect.Execute(s);
			FirePulseSpellEffect.Execute(s);
			LightningSpellEffect.Execute(s);
			PoisonWaveSpellEffect.Execute(s);
			BloodWaveSpellEffect.Execute(s);
			SmiteSpellEffect.Execute(s);
			FlameWaveSpellEffect.Execute(s);
			FireCometSpellEffect.Execute(s);
		}
	}
}
