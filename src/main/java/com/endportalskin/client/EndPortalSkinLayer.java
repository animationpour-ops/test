package com.endportalskin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

/**
 * Couche de rendu ajoutee au rendu du joueur : elle redessine tout le modele du joueur
 * (corps, bras, jambes, tete + couches exterieures du skin) avec le rendu du portail de l'End,
 * donc avec le meme shader et les memes textures que le bloc de portail de l'End.
 */
public class EndPortalSkinLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public EndPortalSkinLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                       AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (player.isInvisible() || !EndSkinState.appliesTo(player)) {
            return;
        }
        this.getParentModel().renderToBuffer(
                poseStack,
                buffer.getBuffer(RenderType.endPortal()),
                packedLight,
                OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F);
    }
}
