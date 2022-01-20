package mc.subsilence.empirewandplus.listeners;

import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import mc.subsilence.empirewandplus.spellEffects.BloodWaveSpellEffect;
import mc.subsilence.empirewandplus.spellEffects.EmpireCometSpellEffect;
import mc.subsilence.empirewandplus.spellEffects.FireballSpellEffect;
import mc.subsilence.empirewandplus.spellEffects.LightningSpellEffect;
import mc.subsilence.empirewandplus.spellEffects.PoisonWaveSpellEffect;

public class ProjectileListener implements Listener {

	@EventHandler
	public void onProjectileLaunch(final ProjectileLaunchEvent e) {

		if (e.getEntity() instanceof Snowball) {
			final Snowball s = (Snowball) e.getEntity();
			EmpireCometSpellEffect.Execute(s);
			FireballSpellEffect.Execute(s);
			LightningSpellEffect.Execute(s);
			PoisonWaveSpellEffect.Execute(s);
			BloodWaveSpellEffect.Execute(s);
		}
	}
}
