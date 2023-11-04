package net.modificationstation.stationapi.api.template.block;

import net.minecraft.block.LeverBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class TemplateLever extends LeverBlock implements BlockTemplate {

    public TemplateLever(Identifier identifier, int j) {
        this(BlockTemplate.getNextId(), j);
        BlockTemplate.onConstructor(this, identifier);
    }

    public TemplateLever(int i, int j) {
        super(i, j);
    }
}
