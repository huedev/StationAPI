package net.modificationstation.stationapi.api.template.block;

import net.minecraft.block.Portal;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class TemplatePortal extends Portal implements BlockTemplate {

    public TemplatePortal(Identifier identifier, int j) {
        this(BlockRegistry.INSTANCE.getNextLegacyId(), j);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplatePortal(int i, int j) {
        super(i, j);
    }
}
