package com.endportalskin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

/** Etat du mod cote client : effet actif ou non, et a qui il s'applique. */
public final class EndSkinState {
    private static boolean enabled = true;

    private EndSkinState() {
    }

    public static boolean isEnabled() {
        return enabled;
    }

    /** Inverse l'etat et renvoie le nouvel etat. */
    public static boolean toggle() {
        enabled = !enabled;
        return enabled;
    }

    /** Vrai si l'effet doit etre dessine sur ce joueur (uniquement le joueur local). */
    public static boolean appliesTo(Player player) {
        if (!enabled) {
            return false;
        }
        Minecraft mc = Minecraft.getInstance();
        return mc.player != null && player.getUUID().equals(mc.player.getUUID());
    }
}
