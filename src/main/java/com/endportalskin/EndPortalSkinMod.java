package com.endportalskin;

import net.minecraftforge.fml.common.Mod;

/**
 * Mod 100 % client : applique l'effet du portail de l'End a tout le skin du joueur.
 * Toute la logique de rendu est dans le paquet {@code com.endportalskin.client}.
 * Cette classe ne reference volontairement aucune classe client.
 */
@Mod(EndPortalSkinMod.MODID)
public class EndPortalSkinMod {
    public static final String MODID = "endportalskin";

    public EndPortalSkinMod() {
        // Rien a enregistrer ici : les evenements client sont declares via @Mod.EventBusSubscriber.
    }
}
