package net.modificationstation.stationloader.mixin.common;

import net.minecraft.packet.AbstractPacket;
import net.modificationstation.stationloader.api.common.event.ModIDEvent;
import net.modificationstation.stationloader.api.common.event.packet.PacketRegister;
import net.modificationstation.stationloader.api.common.registry.ModIDRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(AbstractPacket.class)
public class MixinAbstractPacket {

    @Shadow static void register(int id, boolean flag, boolean flag1, Class<? extends AbstractPacket> arg) {}

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/packet/AbstractPacket;register(IZZLjava/lang/Class;)V", ordinal = 56, shift = At.Shift.AFTER))
    private static void afterVanillaPackets(CallbackInfo ci) {
        ModIDEvent<PacketRegister> event = PacketRegister.EVENT;
        PacketRegister invoker = event.getInvoker();
        String modid = event.getListenerContainer(invoker).getMetadata().getId();
        if (modid != null)
            ModIDRegistry.packet.put(modid, new HashMap<>());
        invoker.registerPackets(MixinAbstractPacket::register, ModIDRegistry.packet.get(modid));
    }
}
