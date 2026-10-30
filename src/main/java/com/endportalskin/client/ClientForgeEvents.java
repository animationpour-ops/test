package com.endportalskin.client;

import com.endportalskin.EndPortalSkinMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/** Evenements du bus Forge (cote client uniquement). */
@Mod.EventBusSubscriber(modid = EndPortalSkinMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEvents {

    private ClientForgeEvents() {
    }

    /** Gere la touche de bascule et affiche l'etat au-dessus de la barre d'objets. */
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        while (ClientModEvents.TOGGLE_KEY.consumeClick()) {
            boolean on = EndSkinState.toggle();
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                mc.player.displayClientMessage(
                        Component.translatable(on ? "message.endportalskin.on" : "message.endportalskin.off"),
                        true);
            }
        }
    }

    /**
     * Bras en vue premiere personne : on remplace le rendu vanilla (bras + manche avec la texture du skin)
     * par le rendu vanilla suivi du meme bras redessine avec le portail de l'End par-dessus.
     */
    @SubscribeEvent
    public static void onRenderArm(RenderArmEvent event) {
        AbstractClientPlayer player = event.getPlayer();
        if (player.isInvisible() || !EndSkinState.appliesTo(player)) {
            return;
        }

        EntityRenderer<? super AbstractClientPlayer> entityRenderer =
                Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(player);
        if (!(entityRenderer instanceof PlayerRenderer renderer)) {
            return;
        }

        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource buffer = event.getMultiBufferSource();
        int light = event.getPackedLight();
        boolean right = event.getArm() == HumanoidArm.RIGHT;

        // Annule le rendu d'origine pour controler l'ordre de dessin (skin d'abord, portail ensuite).
        event.setCanceled(true);

        // Rendu vanilla : prepare aussi le modele (visibilite des manches, pose du bras a 0).
        if (right) {
            renderer.renderRightHand(poseStack, buffer, light, player);
        } else {
            renderer.renderLeftHand(poseStack, buffer, light, player);
        }

        // Meme bras, meme manche, mais avec le rendu du portail de l'End.
        PlayerModel<AbstractClientPlayer> model = renderer.getModel();
        ModelPart arm = right ? model.rightArm : model.leftArm;
        ModelPart sleeve = right ? model.rightSleeve : model.leftSleeve;
        VertexConsumer portal = buffer.getBuffer(RenderType.endPortal());
        arm.render(poseStack, portal, light, OverlayTexture.NO_OVERLAY);
        sleeve.render(poseStack, portal, light, OverlayTexture.NO_OVERLAY);
    }
}
