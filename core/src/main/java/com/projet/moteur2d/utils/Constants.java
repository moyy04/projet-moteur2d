package com.projet.moteur2d.utils;
import com.projet.moteur2d.utils.JsonLoader.GameConfig;

public class Constants {
    public static final int TILE_SIZE = 64;
    public static final int MAP_WIDTH = 30;
    public static final int MAP_HEIGHT = 30;
    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 32;

    // ⬇️⬇️ TOUT DOIT ÊTRE DANS LA CLASSE ⬇️⬇️

    /** Configuration du jeu chargée depuis le fichier JSON. */
    private static final GameConfig CONFIG = JsonLoader.loadConfig();

    /** Vitesse de déplacement du joueur (depuis config.json). */
    public static final float PLAYER_SPEED = CONFIG.playerSpeed;

    /** Dégâts infligés par les ennemis (depuis config.json). */
    public static final int ENEMY_DAMAGE = CONFIG.enemyDamage;

    /**
     * Récupère le titre du jeu.
     * @return Le titre chargé depuis la configuration JSON
     */
    public static String getGameTitle() {
        return CONFIG.gameTitle;
    }
}

