package dev.kurok.mods.gsoxygenfix.mixin;

import by.dragonsurvivalteam.dragonsurvival.registry.attachments.SwimData;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Great Sage fires its SANSO_KINSHOU ("oxygen remaining: minimal") tip
 * whenever a player's air supply drops below 60.
 *
 * Dragon Survival grants water-breathing species (sea dragon, sea dragonborn,
 * and any addon species with the swim ability) unlimited oxygen while they are
 * in their swim form and their eye is in a fluid they can breathe. It marks
 * that state by clamping the air supply to {@link SwimData#UNLIMITED_OXYGEN}
 * (-1), which Great Sage misreads as "about to drown" and warns about every
 * ~30 seconds.
 *
 * This redirect leaves positive-but-low air untouched (a genuine drowning risk
 * still warns), and only reports air as safe when Dragon Survival itself is
 * withholding oxygen: the air is non-positive AND the player's {@link SwimData}
 * reports {@link SwimData#UNLIMITED_OXYGEN} for the fluid their eye is in. A
 * genuine drowning player (no water-breathing ability) has no such bonus, so
 * their warning fires normally.
 */
@Mixin(targets = "dev.dedek.greatsage.server.TipWatcher")
public class TipWatcherMixin {

    @Redirect(
            method = "survival",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;getAirSupply()I"),
            require = 1
    )
    private static int gsoxygenfix$dsUnlimitedOxygen(ServerPlayer player) {
        int air = player.getAirSupply();

        // Positive-but-low air is a genuine drowning risk: warn normally.
        if (air > 0) {
            return air;
        }

        // Air is 0 or below. Distinguish Dragon Survival's unlimited-oxygen
        // marker from a real drowning player by asking Dragon Survival's own
        // swim/oxygen state for the fluid the eye is currently in.
        FluidType eyeFluid = player.getEyeInFluidType();
        int maxOxygen = SwimData.getData(player).getMaxOxygen(player, eyeFluid);
        if (maxOxygen == SwimData.UNLIMITED_OXYGEN) {
            return Integer.MAX_VALUE;
        }

        // Genuine drowning: keep the real (low) air value so the tip fires.
        return air;
    }
}
