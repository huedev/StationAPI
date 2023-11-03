package net.modificationstation.stationapi.mixin.flattening;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.event.item.IsItemSuitableForStateEvent;
import net.modificationstation.stationapi.api.event.item.ItemMiningSpeedMultiplierOnStateEvent;
import net.modificationstation.stationapi.api.item.StationFlatteningItemStack;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.registry.RegistryEntry;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static net.modificationstation.stationapi.api.StationAPI.LOGGER;
import static net.modificationstation.stationapi.api.StationAPI.MODID;
import static net.modificationstation.stationapi.api.registry.Identifier.of;

@Mixin(ItemStack.class)
public abstract class MixinItemInstance implements StationFlatteningItemStack {
    @Shadow public int itemId;

    @Shadow public abstract Item getType();

    @Unique
    private static final String STATION_ID = of(MODID, "id").toString();
    
    @Inject(method = "<init>(Lnet/minecraft/block/BlockBase;)V", at = @At("TAIL"))
    private void onInitFromBlock(Block block, CallbackInfo info) {
        Item item = block.asItem();
        if (item != null) {
            this.itemId = item.id;
        }
    }
    
    @Inject(method = "<init>(Lnet/minecraft/block/BlockBase;I)V", at = @At("TAIL"))
    private void onInitFromBlock(Block block, int count, CallbackInfo info) {
        Item item = block.asItem();
        if (item != null) {
            this.itemId = item.id;
        }
    }
    
    @Inject(method = "<init>(Lnet/minecraft/block/BlockBase;II)V", at = @At("TAIL"))
    private void onInitFromBlock(Block block, int count, int meta, CallbackInfo info) {
        Item item = block.asItem();
        if (item != null) {
            this.itemId = item.id;
        }
    }

    @Redirect(
            method = "toTag(Lnet/minecraft/util/io/CompoundTag;)Lnet/minecraft/util/io/CompoundTag;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/io/CompoundTag;put(Ljava/lang/String;S)V",
                    ordinal = 0
            )
    )
    private void saveIdentifier(NbtCompound instance, String item, short i) {
        instance.putString(STATION_ID, ItemRegistry.INSTANCE.getId(itemId).orElseThrow().toString());
    }

    @Inject(
            method = "fromTag(Lnet/minecraft/util/io/CompoundTag;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/ItemInstance;itemId:I",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            )
    )
    private void loadIdentifier(NbtCompound par1, CallbackInfo ci) {
        String id = par1.getString(STATION_ID);
        if (id.isEmpty())
            LOGGER.warn("Attempted to load an item stack without Station Flattening ID! StationAPI will ignore this and accept the vanilla ID (" + itemId + "), but this should have been handled by DFU beforehand");
        else itemId = Objects.requireNonNull(ItemRegistry.INSTANCE.get(of(id))).id;
    }

    @Override
    public RegistryEntry.Reference<Item> getRegistryEntry() {
        return getType().getRegistryEntry();
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return StationAPI.EVENT_BUS.post(
                IsItemSuitableForStateEvent.builder()
                        .itemStack(ItemStack.class.cast(this))
                        .state(state)
                        .suitable(getType().isSuitableFor(ItemStack.class.cast(this), state))
                        .build()
        ).suitable;
    }

    @Override
    public float getMiningSpeedMultiplier(BlockState state) {
        return StationAPI.EVENT_BUS.post(
                ItemMiningSpeedMultiplierOnStateEvent.builder()
                        .itemStack(ItemStack.class.cast(this))
                        .state(state)
                        .miningSpeedMultiplier(getType().getMiningSpeedMultiplier(ItemStack.class.cast(this), state))
                        .build()
        ).miningSpeedMultiplier;
    }

    @Override
    public boolean isOf(Item item) {
        return this.getType() == item;
    }
}
