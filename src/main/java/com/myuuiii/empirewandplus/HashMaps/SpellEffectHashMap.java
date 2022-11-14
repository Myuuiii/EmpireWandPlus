package com.myuuiii.empirewandplus.HashMaps;

import com.myuuiii.empirewandplus.SpellEffects.Comet.EmpireCometEffect;
import com.myuuiii.empirewandplus.SpellEffects.Comet.FireCometEffect;
import com.myuuiii.empirewandplus.SpellEffects.Fireball.FireballEffect;
import com.myuuiii.empirewandplus.SpellEffects.Lightning.LightningEffect;
import com.myuuiii.empirewandplus.SpellEffects.Pulse.EmpirePulseEffect;
import com.myuuiii.empirewandplus.SpellEffects.Pulse.FirePulseEffect;
import com.myuuiii.empirewandplus.SpellEffects.Smite.SmiteEffect;
import com.myuuiii.empirewandplus.SpellEffects.SpellEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.BloodWaveEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.FlameWaveEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.PoisonWaveEffect;
import com.myuuiii.empirewandplus.SpellNames;

import java.util.HashMap;

public class SpellEffectHashMap {
    public static HashMap<String, SpellEffect> getSpellEffectHashMap() {
        return new HashMap<>() {{
            // Waves
            put(SpellNames.BloodWave, new BloodWaveEffect());
            put(SpellNames.FlameWave, new FlameWaveEffect());
            put(SpellNames.PoisonWave, new PoisonWaveEffect());

            // Comet
            put(SpellNames.EmpireComet, new EmpireCometEffect());
            put(SpellNames.FireComet, new FireCometEffect());

            // Pulse
            put(SpellNames.FirePulse, new FirePulseEffect());
            put(SpellNames.EmpirePulse, new EmpirePulseEffect());

            // Fireball
            put(SpellNames.Fireball, new FireballEffect());

            // Lightning
            put(SpellNames.Lightning, new LightningEffect());

            // Smite
            put(SpellNames.Smite, new SmiteEffect());
        }};
    }
}
