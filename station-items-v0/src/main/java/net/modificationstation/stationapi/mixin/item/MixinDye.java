package net.modificationstation.stationapi.mixin.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.bonemeal.BonemealAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DyeItem.class)
public class MixinDye {
    @Inject(method = "useOnTile", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/level/Level;getTileId(III)I",
            ordinal = 0
    ), cancellable = true)
    private void onBonemealUse(ItemStack item, PlayerEntity player, World level, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> info) {
        BlockState state = level.getBlockState(x, y, z);
        if (state.getBlock().onBonemealUse(level, x, y, z, state)) {
            level.method_202(x, y, z, x, y, z);
            info.setReturnValue(true);
            item.count--;
            return;
        }
        if (BonemealAPI.generate(level, x, y, z, state, side)) {
            level.method_202(x - 8, y - 8, z - 8, x + 8, y + 8, z + 8);
            info.setReturnValue(true);
            item.count--;
        }
    }
}
