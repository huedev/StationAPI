package net.modificationstation.stationapi.api.template.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.util.Identifier;

public class TemplateBlockBase extends Block implements BlockTemplate {

    public TemplateBlockBase(Identifier identifier, Material material) {
        this(BlockTemplate.getNextId(), material);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplateBlockBase(Identifier identifier, int tex, Material material) {
        this(BlockTemplate.getNextId(), tex, material);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplateBlockBase(int id, Material material) {
        super(id, material);
    }

    public TemplateBlockBase(int id, int tex, Material material) {
        super(id, tex, material);
    }
}
