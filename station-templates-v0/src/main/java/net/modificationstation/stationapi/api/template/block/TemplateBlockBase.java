package net.modificationstation.stationapi.api.template.block;

import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class TemplateBlockBase extends BlockBase implements BlockTemplate {

    public TemplateBlockBase(Identifier identifier, Material material) {
        this(BlockRegistry.INSTANCE.getNextLegacyId(), material);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplateBlockBase(Identifier identifier, int tex, Material material) {
        this(BlockRegistry.INSTANCE.getNextLegacyId(), tex, material);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplateBlockBase(int id, Material material) {
        super(id, material);
    }

    public TemplateBlockBase(int id, int tex, Material material) {
        super(id, tex, material);
    }
}
