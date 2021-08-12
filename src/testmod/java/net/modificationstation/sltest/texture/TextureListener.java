package net.modificationstation.sltest.texture;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.resource.TexturePack;
import net.modificationstation.sltest.SLTest;
import net.modificationstation.sltest.block.BlockListener;
import net.modificationstation.sltest.item.ItemListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;
import net.modificationstation.stationapi.api.registry.Identifier;

public class TextureListener {

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
//        BlockListener.testBlock.texture = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("TERRAIN"), "/assets/sltest/textures/blocks/testBlock.png");
//        BlockListener.testAnimatedBlock.texture = TextureFactory.INSTANCE.addAnimatedTexture(TextureRegistry.getRegistry("TERRAIN"), "/assets/sltest/textures/blocks/testAnimatedBlock.png", 1);
//        BlockListener.Freezer.texture = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry(TextureRegistry.Vanilla.TERRAIN), "/assets/sltest/textures/blocks/FreezerTop.png");
//        BlockListener.Freezer.sideTexture = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry(TextureRegistry.Vanilla.TERRAIN), "/assets/sltest/textures/blocks/FreezerSide.png");
        //BlockListener.testBlock.texture = BlockBase.WOOL.texture;

//        ItemListener.testItem.setTexturePosition(TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("GUI_ITEMS"), "/assets/sltest/textures/items/testItem.png"));
//        ItemListener.testPickaxe.setTexturePosition(TextureFactoryOld.INSTANCE.addAnimatedTexture(TextureRegistryOld.getRegistry("GUI_ITEMS"), "/assets/sltest/textures/items/testPickaxe.png", 4));
//        ItemListener.testNBTItem.setTexturePosition(TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("GUI_ITEMS"), "/assets/sltest/textures/items/nbtItem.png"));

        BlockListener.testBlock.texture = ExpandableAtlas.STATION_TERRAIN.addTexture("/assets/sltest/textures/blocks/testBlock.png").index;

        ItemListener.testNBTItem.setTexture("/assets/sltest/textures/items/nbtItem.png");
        ItemListener.testItem.setTexture("/assets/sltest/textures/items/highres.png");
        ItemListener.testPickaxe.setAnimationBinder( "/assets/sltest/textures/items/testPickaxe.png", 1, "/assets/sltest/textures/items/testItem.png");

//        SquareAtlas.GUI_ITEMS.addAnimationBinder("/assets/sltest/textures/items/testPickaxe.png", 1, 0);

        System.out.println(TexturePack.class.getResourceAsStream("/assets/sltest/textures/items/testItem.png"));

        TEST_ATLAS = new ExpandableAtlas(Identifier.of(SLTest.MODID, "test_atlas"));

        TEST_ATLAS.addTexture("/assets/sltest/textures/items/testItem.png");
        TEST_ATLAS.addTexture("/assets/sltest/textures/blocks/testBlock.png");
        TEST_ATLAS.addTexture("/assets/sltest/textures/blocks/testAnimatedBlock.png");
        TEST_ATLAS.addTexture("/assets/sltest/textures/items/testPickaxe.png");
        TEST_ATLAS.addTexture("/assets/sltest/textures/items/nbtItem.png");
        TEST_ATLAS.addTexture("/assets/sltest/textures/blocks/FreezerTop.png");
        TEST_ATLAS.addTexture("/assets/sltest/textures/blocks/FreezerSide.png");
    }

    public static ExpandableAtlas TEST_ATLAS;
}