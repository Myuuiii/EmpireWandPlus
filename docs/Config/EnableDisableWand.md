# Enabling and Disabling wands

Wands can be disabled through the configuration of the plugin. This will not prohibit users from using the `wand` command to obtain it. It will block the wand from casting and switching spells.

```yaml
Wands:
  EmpireWand:
    Enabled: true
    UseCustomSpellSet: false
    Spells:
    
...
```

You can disable a wand by setting the "Enabled" property of the wand you want to disable to "false". To re-enable it you can just switch it back to "true". 

Please note that before any changes will be perceptible in game, the plugin must first be reloaded.

