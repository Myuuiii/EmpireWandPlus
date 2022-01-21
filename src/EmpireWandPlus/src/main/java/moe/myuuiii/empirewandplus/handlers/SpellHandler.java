package moe.myuuiii.empirewandplus.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.Spells;
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
				BloodSparkSpell.Execute(loc, p);
				break;
			case Spells.BloodWave:
				BloodWaveSpell.Execute(loc, p);
				break;

			//
			// Celestial wand
			//
			case Spells.Lightning:
				LightningSpell.Execute(loc, p);
				break;

			//
			// Empire wand
			//
			case Spells.Launch:
				LaunchSpell.Execute(loc, p);
				break;
			case Spells.Fireball:
				FireballSpell.Execute(loc, p);
				break;
			case Spells.EmpireComet:
				EmpireCometSpell.Execute(loc, p);
				break;
			case Spells.EmpireSpark:
				EmpireSparkSpell.Execute(loc, p);
				break;

			//
			// Poison scythe wand
			//
			case Spells.PoisonWave:
				PoisonWaveSpell.Execute(loc, p);
				break;
			case Spells.PoisonSpark:
				PoisonSparkSpell.Execute(loc, p);
				break;

			//
			// Spells to be removed
			//
			case Spells.Cloud:
				CloudSpell.Execute(loc, p);
				break;
		}
	}
}
