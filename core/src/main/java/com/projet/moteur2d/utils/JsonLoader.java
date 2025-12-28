package com.projet.moteur2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 * Chargeur de fichiers JSON pour la configuration du jeu.
 * <p>
 * Cette classe permet de lire des fichiers JSON et de convertir
 * leur contenu en objets Java.
 * </p>
 *
 * @author Ahlame Lamkiki
 * @version 1.0
 */
public class JsonLoader {

    /**
     * Charge la configuration du jeu depuis un fichier JSON.
     * <p>
     * Si le fichier n'existe pas, retourne une configuration par défaut.
     * </p>
     *
     * @return La configuration du jeu
     */
    public static GameConfig loadConfig() {
        Json json = new Json();
        FileHandle file = Gdx.files.internal("config.json");

        // Si le fichier n'existe pas, crée une config par défaut
        if (!file.exists()) {
            Gdx.app.error("JsonLoader", "config.json non trouvé, utilisation des valeurs par défaut");
            return new GameConfig();
        }

        String jsonText = file.readString();
        return json.fromJson(GameConfig.class, jsonText);
    }

    /**
     * Classe interne représentant la configuration du jeu.
     * <p>
     * Les champs publics sont automatiquement remplis par le parseur JSON.
     * </p>
     */
    public static class GameConfig {
        /** Titre du jeu affiché dans les logs */
        public String gameTitle = "Mon Jeu 2D";

        /** Vitesse de déplacement du joueur en pixels par seconde */
        public float playerSpeed = 200f;
        /**
         * Constructeur par défaut.
         * <p>
         * Utilisé lorsque le fichier JSON n'existe pas.
         * </p>
         */
        public GameConfig() {}
    }
}
