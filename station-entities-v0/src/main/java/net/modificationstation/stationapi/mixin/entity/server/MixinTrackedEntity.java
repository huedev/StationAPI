package net.modificationstation.stationapi.mixin.entity.server;

import net.minecraft.class_174;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.modificationstation.stationapi.api.server.entity.CustomSpawnDataProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_174.class)
public class MixinTrackedEntity {

    @Shadow
    public Entity entityToSync;

    @Inject(method = "method_600", at = @At(value = "HEAD"), cancellable = true)
    private void getSpawnData(CallbackInfoReturnable<Packet> cir) {
        if (this.entityToSync instanceof CustomSpawnDataProvider provider)
            cir.setReturnValue(provider.getSpawnData());
    }
}
