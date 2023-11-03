package net.modificationstation.stationapi.mixin.dimension.server;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.modificationstation.stationapi.api.world.dimension.VanillaDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayerPacketHandler {

    @Shadow private ServerPlayerEntity serverPlayer;

    @SuppressWarnings("DefaultAnnotationParam")
    @ModifyConstant(
            method = "onRespawn(Lnet/minecraft/packet/play/Respawn0x9C2SPacket;)V",
            constant = @Constant(intValue = 0)
    )
    private int modifyRespawnDimension(int original) {
        return serverPlayer.world.dimension.method_1766() ? serverPlayer.dimensionId : DimensionRegistry.INSTANCE.getLegacyId(VanillaDimensions.OVERWORLD).orElseThrow(() -> new IllegalStateException("Overworld not found!"));
    }
}
