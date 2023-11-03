package net.modificationstation.stationapi.mixin.vanillafix.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Screen.class)
public interface ScreenBaseAccessor {

    @Accessor
    Minecraft getMinecraft();
}
