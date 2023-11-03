package net.modificationstation.stationapi.mixin.flattening;

import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.entity.player.StationFlatteningPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerBase extends LivingEntity implements StationFlatteningPlayerEntity {

    @Shadow public PlayerInventory inventory;

    public MixinPlayerBase(World arg) {
        super(arg);
    }

    @Override
    public boolean canHarvest(BlockState state) {
        return inventory.canHarvest(state);
    }

    @Override
    public float getBlockBreakingSpeed(BlockState state) {
        float f = inventory.getBlockBreakingSpeed(state);
        if (isInFluid(Material.WATER)) f /= 5.0f;
        if (!field_1623) f /= 5.0f;
        return f;
    }
}
