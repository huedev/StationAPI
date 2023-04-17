package net.modificationstation.stationapi.api.template.block;

import net.minecraft.block.Mushroom;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class TemplateMushroom extends Mushroom implements BlockTemplate {

    public TemplateMushroom(Identifier identifier, int j) {
        this(BlockRegistry.INSTANCE.getNextLegacyId(), j);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplateMushroom(int i, int j) {
        super(i, j);
    }
}
