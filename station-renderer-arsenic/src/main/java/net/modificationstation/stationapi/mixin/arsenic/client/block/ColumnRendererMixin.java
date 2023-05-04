package net.modificationstation.stationapi.mixin.arsenic.client.block;

import net.minecraft.block.BlockBase;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderer;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.modificationstation.stationapi.impl.client.arsenic.renderer.render.ArsenicBlockRenderer.*;

@Mixin(BlockRenderer.class)
public class ColumnRendererMixin {

    private Sprite stationapi_column_texture;

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @Inject(
            method = "method_56",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/render/block/BlockRenderer;textureOverride:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 1,
                    shift = At.Shift.BY,
                    by = 3
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void stationapi_column_captureTexture(
            BlockBase block, int d, double e, double f, double par5, CallbackInfo ci,
            Tessellator var9, int texture
    ) {
        stationapi_column_texture = block.getAtlas().getTexture(texture).getSprite();
    }

    @ModifyVariable(
            method = "method_56",
            index = 11,
            at = @At("STORE")
    )
    private int stationapi_column_modTextureX(int value) {
        return stationapi_column_texture.getX();
    }

    @ModifyVariable(
            method = "method_56",
            index = 12,
            at = @At("STORE")
    )
    private int stationapi_column_modTextureY(int value) {
        return stationapi_column_texture.getY();
    }

    @ModifyConstant(
            method = "method_56",
            constant = {
                    @Constant(
                            floatValue = ATLAS_SIZE,
                            ordinal = 0
                    ),
                    @Constant(
                            floatValue = ATLAS_SIZE,
                            ordinal = 1
                    )
            }
    )
    private float stationapi_column_modAtlasWidth(float constant) {
        return StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).getWidth();
    }

    @ModifyConstant(
            method = "method_56",
            constant = @Constant(
                    floatValue = ADJUSTED_TEX_SIZE,
                    ordinal = 0
            )
    )
    private float stationapi_column_modTextureWidth(float constant) {
        return adjustToWidth(constant, stationapi_column_texture);
    }

    @ModifyConstant(
            method = "method_56",
            constant = {
                    @Constant(
                            floatValue = ATLAS_SIZE,
                            ordinal = 2
                    ),
                    @Constant(
                            floatValue = ATLAS_SIZE,
                            ordinal = 3
                    )
            }
    )
    private float stationapi_column_modAtlasHeight(float constant) {
        return StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).getHeight();
    }

    @ModifyConstant(
            method = "method_56",
            constant = @Constant(
                    floatValue = ADJUSTED_TEX_SIZE,
                    ordinal = 1
            )
    )
    private float stationapi_column_modTextureHeight(float constant) {
        return adjustToHeight(constant, stationapi_column_texture);
    }

    @Inject(
            method = "method_56",
            at = @At("HEAD")
    )
    private void stationapi_column_releaseCaptured(BlockBase i, int d, double e, double f, double par5, CallbackInfo ci) {
        stationapi_column_texture = null;
    }
}
