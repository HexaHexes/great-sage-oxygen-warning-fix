# Great Sage Oxygen Warning Fix

A tiny NeoForge mixin mod for the **Tensura RPG** pack (MC 1.21.1 / NeoForge 21.1.259) that fixes a false warning from the Great Sage ability.

## What it does

Great Sage shows a low-oxygen survival tip (`sanso_kinshou`) whenever the player's air supply drops below 60. Dragon Survival gives water-breathing species (sea dragon / sea dragonborn) an "unlimited oxygen" air supply of **−1**, so those players were told they were suffocating while perfectly safe.

This mod redirects `ServerPlayer.getAirSupply()` inside Great Sage's `TipWatcher.survival`, reporting air ≤ 0 as `Integer.MAX_VALUE`. Result:

- Air ≤ 0 (Dragon Survival's unlimited-oxygen marker) → **no warning**
- Air 1–59 → warning fires **normally**

## Dependencies (all required — load fails without them)

| Mod | Version range | Why |
|---|---|---|
| NeoForge | `[21.1,)` | Platform |
| Minecraft | `[1.21.1,1.21.2)` | Platform |
| Great Sage (`greatsage`) | `[0.0.6,)` | Mixin target lives here (`dev.dedek.greatsage.server.TipWatcher`) |
| Dragon Survival (`dragonsurvival`) | `[2.0,)` | Source of the −1 unlimited-oxygen marker this fix exists for |

## Build

Requires JDK 21.

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
gradle build
```

Output: `build\libs\gsoxygenfix-1.0.0.jar`

## Install

Copy the jar into the instance's `mods\` folder.

The mixin config is `"required": false`, so if a future Great Sage update changes `TipWatcher`, the mixin disables itself with a log error instead of crashing the game.
