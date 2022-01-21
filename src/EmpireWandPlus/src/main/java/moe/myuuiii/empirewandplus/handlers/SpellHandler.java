package moe.myuuiii.empirewandplus.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.spells.BloodSparkSpell;
import moe.myuuiii.empirewandplus.spells.BloodWaveSpell;
import moe.myuuiii.empirewandplus.spells.CloudSpell;
import moe.myuuiii.empirewandplus.spells.EmpireCometSpell;
import moe.myuuiii.empirewandplus.spells.EmpireSparkSpell;
import moe.myuuiii.empirewandplus.spells.FireballSpell;
import moe.myuuiii.empirewandplus.spells.LaunchSpell;
import moe.myuuiii.empirewandplus.spells.LightningSpell;
import moe.myuuiii.empirewandplus.spells.PoisonSparkSpell;
import moe.myuuiii.empirewandplus.spells.PoisonWaveSpell;

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
			case "Blood Spark":
				BloodSparkSpell.Execute(loc, p);
				break;
			case "Blood Wave":
				BloodWaveSpell.Execute(loc, p);
				break;

			//
			// Celestial wand
			//
			case "Lightning":
				LightningSpell.Execute(loc, p);
				break;

			//
			// Empire wand
			//
			case "Launch":
				LaunchSpell.Execute(loc, p);
				break;
			case "Fireball":
				FireballSpell.Execute(loc, p);
				break;
			case "Empire Comet":
				EmpireCometSpell.Execute(loc, p);
				break;
			case "Empire Spark":
				EmpireSparkSpell.Execute(loc, p);
				break;

			//
			// Poison scythe wand
			//
			case "Poison Wave":
				PoisonWaveSpell.Execute(loc, p);
				break;
			case "Poison Spark":
				PoisonSparkSpell.Execute(loc, p);
				break;

			//
			// Spells to be removed
			//
			case "Cloud":
				CloudSpell.Execute(loc, p);
				break;
		}
	}
}
