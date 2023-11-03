package net.modificationstation.stationapi.mixin.render.client;

import net.minecraft.class_279;
import net.modificationstation.stationapi.api.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_279.class)
public interface WaterColourAccessor {

    @Accessor("map")
    static int[] stationapi$getMap() {
        return Util.assertMixin();
    }
}
