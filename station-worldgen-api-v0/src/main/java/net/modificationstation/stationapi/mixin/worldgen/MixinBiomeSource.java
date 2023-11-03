package net.modificationstation.stationapi.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.class_153;
import net.minecraft.class_519;
import net.modificationstation.stationapi.api.worldgen.BiomeAPI;
import net.modificationstation.stationapi.api.worldgen.biome.BiomeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_519.class)
public class MixinBiomeSource {
    @Inject(
            method = "getBiomes([Lnet/minecraft/level/biome/Biome;IIII)[Lnet/minecraft/level/biome/Biome;",
            at = @At("HEAD")
    )
    private void capturePosition(
            class_153[] data, int x, int z, int dx, int dz, CallbackInfoReturnable<class_153[]> info,
            @Share("posX") LocalIntRef posX, @Share("posZ") LocalIntRef posZ
    ) {
        posX.set(x);
        posZ.set(z);
    }

    @WrapOperation(
            method = "getBiomes([Lnet/minecraft/level/biome/Biome;IIII)[Lnet/minecraft/level/biome/Biome;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/biome/Biome;getBiome(DD)Lnet/minecraft/level/biome/Biome;"
            )
    )
    private class_153 getRegionBiome(
            double temperature, double wetness, Operation<class_153> original,
            @Local(index = 7) int dx, @Local(index = 8) int dz,
            @Share("posX") LocalIntRef posX, @Share("posZ") LocalIntRef posZ
    ) {
        BiomeProvider provider = BiomeAPI.getOverworldProvider();
        return provider.getBiome(posX.get() + dx, posZ.get() + dz, (float) temperature, (float) wetness);
    }
}
