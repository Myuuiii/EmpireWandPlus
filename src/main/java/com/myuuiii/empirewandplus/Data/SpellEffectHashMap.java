package com.myuuiii.empirewandplus.Data;

import com.myuuiii.empirewandplus.Abstracts.ProjectileSpellEffect;
import com.myuuiii.empirewandplus.SpellEffects.Comet.EmpireCometProjectileEffect;
import com.myuuiii.empirewandplus.SpellEffects.Comet.FireCometProjectileEffect;
import com.myuuiii.empirewandplus.SpellEffects.Fireball.FireballEffect;
import com.myuuiii.empirewandplus.SpellEffects.Lightning.LightningProjectileEffect;
import com.myuuiii.empirewandplus.SpellEffects.Pulse.EmpirePulseProjectileEffect;
import com.myuuiii.empirewandplus.SpellEffects.Pulse.FirePulseProjectileEffect;
import com.myuuiii.empirewandplus.SpellEffects.Smite.SmiteEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.BloodWaveProjectileEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.FlameWaveProjectileEffect;
import com.myuuiii.empirewandplus.SpellEffects.Wave.PoisonWaveProjectileEffect;

import java.util.HashMap;

public class SpellEffectHashMap {
    public static HashMap<String, ProjectileSpellEffect> getSpellEffectHashMap() {
        return new HashMap<>() {{
            // Waves
            put(SpellNames.BloodWave, new BloodWaveProjectileEffect());
            put(SpellNames.FlameWave, new FlameWaveProjectileEffect());
            put(SpellNames.PoisonWave, new PoisonWaveProjectileEffect());

            // Comet
            put(SpellNames.EmpireComet, new EmpireCometProjectileEffect());
            put(SpellNames.FireComet, new FireCometProjectileEffect());

            // Pulse
            put(SpellNames.FirePulse, new FirePulseProjectileEffect());
            put(SpellNames.EmpirePulse, new EmpirePulseProjectileEffect());

            // Fireball
            put(SpellNames.Fireball, new FireballEffect());

            // Lightning
            put(SpellNames.Lightning, new LightningProjectileEffect());

            // Smite
            put(SpellNames.Smite, new SmiteEffect());
        }};
    }
}
