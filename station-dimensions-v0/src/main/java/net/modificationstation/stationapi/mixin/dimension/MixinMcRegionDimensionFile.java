package net.modificationstation.stationapi.mixin.dimension;

import net.minecraft.level.dimension.Dimension;
import net.minecraft.level.dimension.McRegionDimensionFile;
import net.modificationstation.stationapi.api.level.dimension.VanillaDimensions;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(McRegionDimensionFile.class)
public class MixinMcRegionDimensionFile {

    @Redirect(
            method = "getChunkIO(Lnet/minecraft/level/dimension/Dimension;)Lnet/minecraft/level/chunk/ChunkIO;",
            at = @At(
                    value = "CONSTANT",
                    args = "classValue=net/minecraft/level/dimension/Nether"
            )
    )
    private boolean isNotOverworld(Object dimension, Class<?> netherClass) {
        return !DimensionRegistry.INSTANCE.getIdentifier(((Dimension) dimension).id).map(VanillaDimensions.OVERWORLD::equals).orElse(true);
    }

    @ModifyConstant(
            method = "getChunkIO(Lnet/minecraft/level/dimension/Dimension;)Lnet/minecraft/level/chunk/ChunkIO;",
            constant = @Constant(stringValue = "DIM-1")
    )
    private String modifyDimensionPath(String constant, Dimension dimension) {
        return "DIM" + dimension.id;
    }
}