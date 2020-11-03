package net.modificationstation.stationloader.api.common.event.block;

import net.modificationstation.stationloader.api.common.event.ModIDEvent;
import net.modificationstation.stationloader.api.common.factory.EventFactory;

public interface BlockRegister {

    ModIDEvent<BlockRegister> EVENT = EventFactory.INSTANCE.newModIDEvent(BlockRegister.class, listeners ->
            () -> {
                for (BlockRegister listener : listeners) {
                    BlockRegister.EVENT.setCurrentListener(listener);
                    listener.registerBlocks();
                }
            });

    void registerBlocks();
}
