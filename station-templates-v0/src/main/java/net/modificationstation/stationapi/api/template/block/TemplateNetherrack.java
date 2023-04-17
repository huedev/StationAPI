package net.modificationstation.stationapi.api.template.block;

import net.minecraft.block.Netherrack;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class TemplateNetherrack extends Netherrack implements BlockTemplate {

    public TemplateNetherrack(Identifier identifier, int j) {
        this(BlockRegistry.INSTANCE.getNextLegacyId(), j);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplateNetherrack(int i, int j) {
        super(i, j);
    }
}
