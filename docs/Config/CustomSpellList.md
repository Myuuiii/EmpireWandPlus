# Custom Spells List

You can change the list of spells bound to a wand by changing a few things in the configuration file. This feature might be useful when you are making a minigame that makes use of this plugin and you dont want certain spells to be bound to the wand.

```yaml
Wands:
  EmpireWand:
    Enabled: true
    UseCustomSpellSet: true
    Spells:
   		- Fireball
   		- Empire Spark
```

To change to the custom spell list you will need to change the "UseCustomSpellSet" property of a wand to "true" and suppply the spells that you want to bind to the wand instead. These spell names must be the full spell names (not the spell configuration name). These names can be found [here](../Spells/All.md). Please see the code block above as a reference for the custom spell set configuration. If you want to revert to the standard spell set you can simply change the "UseCustomSpellSet" property to "false". You can leave the "Spells" property list filled, these will not be used unless "UseCustomSpellSet" is set to true.

Please note that before any changes will be perceptible in game, the plugin must first be reloaded.