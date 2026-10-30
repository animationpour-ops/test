package com.endportalskin.client;

import com.endportalskin.EndPortalSkinMod;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/** Evenements du bus du mod (cote client uniquement). */
@Mod.EventBusSubscriber(modid = EndPortalSkinMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {

    /** Touche pour activer / desactiver l'effet (K par defaut, modifiable dans Options > Controles). */
    public static final KeyMapping TOGGLE_KEY = new KeyMapping(
            "key.endportalskin.toggle",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            "key.categories.endportalskin");

    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_KEY);
    }

    /** Ajoute la couche portail de l'End aux deux modeles de joueur : "default" (Steve) et "slim" (Alex). */
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        for (String skinName : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(skinName);
            if (renderer != null) {
                renderer.addLayer(new EndPortalSkinLayer(renderer));
            }
        }
    }
}
