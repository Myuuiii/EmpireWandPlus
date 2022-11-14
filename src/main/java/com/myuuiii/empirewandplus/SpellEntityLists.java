package com.myuuiii.empirewandplus;

import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.UUID;

public class SpellEntityLists {

    // Ranged - Misc
    public static final ArrayList<Entity> LIGHTNING_ENTITIES = new ArrayList<>();
    public static final ArrayList<Entity> SMITE_ENTITIES = new ArrayList<>();
    public static final ArrayList<Entity> EMPIRE_COMET_ENTITIES = new ArrayList<>();

    public static final ArrayList<Entity> FIRE_COMET_ENTITIES = new ArrayList<>();
    public static final ArrayList<Entity> FIRE_PULSE_ENTITIES = new ArrayList<>();
    public static final ArrayList<Entity> FIREBALL_ENTITIES = new ArrayList<>();
    public static final ArrayList<Entity> EMPIRE_PULSE_ENTITIES = new ArrayList<>();

    // Ranged - With User Lists
    public static final ArrayList<Entity> BLOOD_WAVE_ENTITIES = new ArrayList<>();
    public static final ArrayList<UUID> BLOOD_WAVE_PLAYERS = new ArrayList<UUID>();

    public static final ArrayList<Entity> POISON_WAVE_ENTITIES = new ArrayList<>();
    public static final ArrayList<UUID> POISON_WAVE_PLAYERS = new ArrayList<>();

    public static final ArrayList<Entity> FLAME_WAVE_ENTITIES = new ArrayList<>();
    public static final ArrayList<UUID> FLAME_WAVE_PLAYERS = new ArrayList<>();

    // Leap Spell
    public static final ArrayList<UUID> LEAP_PLAYERS = new ArrayList<>();
}
