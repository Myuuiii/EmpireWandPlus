# Enabling and Disabling spells

In the configuration you can define which spells are enabled and which ones are disabled. Sometimes you might want to disable a spell if it is causing issues on your server but still have it be in the wand's spell list. 

```yaml
Spells:
  Blood Cloud:
    Enabled: true
```

To disable a spell, change the "Enabled" property of the spell to "false", if you want to re-enable the spell you can change it back to "true".

Please note that before any changes will be perceptible in game, the plugin must first be reloaded.