package net.modificationstation.stationapi.api.template.block;

import net.minecraft.block.Ice;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class TemplateIce extends Ice implements BlockTemplate {

    public TemplateIce(Identifier identifier, int j) {
        this(BlockRegistry.INSTANCE.getNextLegacyId(), j);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplateIce(int i, int j) {
        super(i, j);
    }
}
