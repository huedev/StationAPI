package net.modificationstation.stationapi.api.template.block;

import net.minecraft.block.TntBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class TemplateTnt extends TntBlock implements BlockTemplate {
    
    public TemplateTnt(Identifier identifier, int j) {
        this(BlockTemplate.getNextId(), j);
        BlockTemplate.onConstructor(this, identifier);
    }
    
    public TemplateTnt(int i, int j) {
        super(i, j);
    }
}
