package moe.myuuiii.empirewandplus.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.Extensions;
import moe.myuuiii.empirewandplus.Spells;
import moe.myuuiii.empirewandplus.managers.ConfigManager;
import moe.myuuiii.empirewandplus.spells.*;

public class SpellHandler {
	public static void HandleSpellByName(String spellName, Location loc, Player p) {
		//
		//
		// General spell handler
		//
		//
		switch (spellName) {
			//
			// Blood wand
			//
			case Spells.BloodSpark:
				if (ConfigManager.getSpellEnabled(Spells.BloodSpark, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.BloodSpark), loc, p))
						BloodSparkSpell.Execute(loc, p);
				break;
			case Spells.BloodWave:
				if (ConfigManager.getSpellEnabled(Spells.BloodWave, p))
					BloodWaveSpell.Execute(loc, p);
				break;
			case Spells.BloodCloud:
				if (ConfigManager.getSpellEnabled(Spells.BloodCloud, p))
					BloodCloudSpell.Execute(loc, p);
				break;

			//
			// Celestial wand
			//
			case Spells.Lightning:
				if (ConfigManager.getSpellEnabled(Spells.Lightning, p))
					LightningSpell.Execute(loc, p);
				break;
			case Spells.CelestialCloud:
				if (ConfigManager.getSpellEnabled(Spells.CelestialCloud, p))
					CelestialCloudSpell.Execute(loc, p);
				break;
			case Spells.CelestialStun:
				if (ConfigManager.getSpellEnabled(Spells.CelestialStun, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.CelestialStun), loc, p))
						CelestialStunSpell.Execute(loc, p);
				break;
			case Spells.CelestialConfuse:
				if (ConfigManager.getSpellEnabled(Spells.CelestialConfuse, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.CelestialConfuse), loc, p))
						CelestialConfuseSpell.Execute(loc, p);
				break;
			case Spells.Smite:
				if (ConfigManager.getSpellEnabled(Spells.Smite, p))
					SmiteSpell.Execute(loc, p);
				break;

			//
			// Empire wand
			//
			case Spells.Launch:
				if (ConfigManager.getSpellEnabled(Spells.Launch, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.Launch), loc, p))
						LaunchSpell.Execute(loc, p);
				break;
			case Spells.EmpireComet:
				if (ConfigManager.getSpellEnabled(Spells.EmpireComet, p))
					EmpireCometSpell.Execute(loc, p);
				break;
			case Spells.EmpireSpark:
				if (ConfigManager.getSpellEnabled(Spells.EmpireSpark, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.EmpireSpark), loc, p))
						EmpireSparkSpell.Execute(loc, p);
				break;
			case Spells.EmpireCloud:
				if (ConfigManager.getSpellEnabled(Spells.EmpireCloud, p))
					EmpireCloudSpell.Execute(loc, p);
				break;
			case Spells.EmpireStun:
				if (ConfigManager.getSpellEnabled(Spells.EmpireStun, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.EmpireStun), loc, p))
						EmpireStunSpell.Execute(loc, p);
				break;
			case Spells.EmpireConfuse:
				if (ConfigManager.getSpellEnabled(Spells.EmpireConfuse, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.EmpireConfuse), loc, p))
						EmpireConfuseSpell.Execute(loc, p);
				break;
			case Spells.EmpireBlink:
				if (ConfigManager.getSpellEnabled(Spells.EmpireBlink, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.EmpireBlink), loc, p))
						EmpireBlinkSpell.Execute(loc, p);
				break;
			case Spells.Capture:
				if (ConfigManager.getSpellEnabled(Spells.Capture, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.Capture), loc, p))
						CaptureSpell.Execute(loc, p);
				break;
			case Spells.Leap:
				if (ConfigManager.getSpellEnabled(Spells.Leap, p))
					LeapSpell.Execute(loc, p);
				break;

			//
			// Hell wand
			//
			case Spells.Fireball:
				if (ConfigManager.getSpellEnabled(Spells.Fireball, p))
					FireballSpell.Execute(loc, p);
				break;
			case Spells.FirePulse:
				if (ConfigManager.getSpellEnabled(Spells.FirePulse, p))
					FirePulseSpell.Execute(loc, p);
				break;
			case Spells.Ignite:
				if (ConfigManager.getSpellEnabled(Spells.Ignite, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.Ignite), loc, p))
						IgniteSpell.Execute(loc, p);
				break;
			case Spells.FlameWave:
				if (ConfigManager.getSpellEnabled(Spells.FlameWave, p))
					FlameWaveSpell.Execute(loc, p);
				break;
			case Spells.FireComet:
				if (ConfigManager.getSpellEnabled(Spells.FireComet, p))
					FireCometSpell.Execute(loc, p);
				break;

			//
			// Poison scythe wand
			//
			case Spells.PoisonWave:
				if (ConfigManager.getSpellEnabled(Spells.PoisonWave, p))
					PoisonWaveSpell.Execute(loc, p);
				break;
			case Spells.PoisonSpark:
				if (ConfigManager.getSpellEnabled(Spells.PoisonSpark, p))
					if (Extensions.CheckIfInRange(ConfigManager.getSpellRange(Spells.PoisonSpark), loc, p))
						PoisonSparkSpell.Execute(loc, p);
				break;
			case Spells.PoisonCloud:
				if (ConfigManager.getSpellEnabled(Spells.PoisonCloud, p))
					PoisonCloudSpell.Execute(loc, p);
				break;
		}
	}
}
