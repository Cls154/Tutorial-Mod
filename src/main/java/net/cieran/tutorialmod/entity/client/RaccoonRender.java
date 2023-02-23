package net.cieran.tutorialmod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.cieran.tutorialmod.TutorialMod;
import net.cieran.tutorialmod.entity.custom.RaccoonEntity;
import net.cieran.tutorialmod.entity.variant.RaccoonVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class RaccoonRender extends GeoEntityRenderer<RaccoonEntity> {
    public static Map<RaccoonVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RaccoonVariant.class), (p_114874) -> {
                p_114874.put(RaccoonVariant.DEFAULT, new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/raccoon/raccoon.png"));
                p_114874.put(RaccoonVariant.DARK, new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/raccoon/raccoondark.png"));
                p_114874.put(RaccoonVariant.RED, new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/raccoon/redraccoon.png"));
            });

    public RaccoonRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RaccoonModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(RaccoonEntity instance) {
        return RaccoonRender.LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderType getRenderType(RaccoonEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        if (animatable.isBaby()) {
            stack.scale(0.4f, 0.4f, 0.4f);
        } else {
            stack.scale(0.8f, 0.8f, 0.8f);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
