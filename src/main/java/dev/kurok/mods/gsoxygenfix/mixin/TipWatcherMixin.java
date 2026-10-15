package dev.kurok.mods.gsoxygenfix.mixin;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Great Sage fires its SANSO_KINSHOU ("oxygen remaining: minimal") tip
 * whenever a player's air supply drops below 60. Dragon Survival marks
 * unlimited oxygen for water-breathing species (sea dragon / sea
 * dragonborn) by forcing the air supply to -1, which Great Sage
 * misreads as "about to drown" and warns about every 30 seconds.
 *
 * This redirect reports air values of 0 or below as a safe value, so
 * the tip only fires on positive-but-low air (a genuine drowning risk)
 * and stays silent for Dragon Survival's unlimited-oxygen marker.
 */
@Mixin(targets = "dev.dedek.greatsage.server.TipWatcher")
public class TipWatcherMixin {

    @Redirect(
            method = "survival",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;getAirSupply()I")
    )
    private static int gsoxygenfix$onlyPositiveAir(ServerPlayer player) {
        int air = player.getAirSupply();
        return air <= 0 ? Integer.MAX_VALUE : air;
    }
}
