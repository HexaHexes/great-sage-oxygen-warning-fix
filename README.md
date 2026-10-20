# Great Sage Oxygen Warning Fix

Fixes a false "you're running out of oxygen" warning from the Great Sage ability for
Dragon Survival's water-breathing species.

## The problem

Great Sage shows a low-oxygen survival tip whenever your air drops below 60. Dragon
Survival gives water-breathing species (sea dragon, sea dragonborn) an "unlimited
oxygen" supply of **−1** — so those players were being told they were suffocating while
perfectly safe underwater.

## What it does

- Air **at or below 0** (Dragon Survival's unlimited-oxygen marker) → **no warning**.
- Air **1–59** → the warning still fires **normally**.

Nothing else about Great Sage is changed.

## Getting it in-game

Install the jar and play — no configuration. If a future Great Sage update changes
something the mod patches, it disables itself with a log error instead of crashing.

## Dependencies

All required — the mod won't load without them:

| Mod | Why |
|---|---|
| [Great Sage](https://www.curseforge.com/minecraft/mc-mods/great-sage) | The ability being fixed |
| [Dragon Survival](https://www.curseforge.com/minecraft/mc-mods/dragon-survival) | The water-breathing species this is for |

Made for the **Tensura RPG** pack (Minecraft 1.21.1, NeoForge).

## License

GNU Lesser General Public License v2.1 — see [`LICENSE`](LICENSE).
Copyright (c) 2026 Bitwise Madness.
