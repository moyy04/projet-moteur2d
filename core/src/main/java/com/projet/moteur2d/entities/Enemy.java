package com.projet.moteur2d.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe représentant un ennemi simple dans le jeu.
 * <p>
 * Les ennemis sont des entités statiques qui causent la défaite du joueur
 * en cas de collision. Leur position et taille sont définies dans Tiled.
 * </p>
 *
 * @author [Ton nom]
 * @version 1.0
 * @see Entity
 */

public class Enemy extends Entity {
    private Texture texture;

    /**
     * Construit un nouvel ennemi.
     *
     * @param x Position X en pixels
     * @param y Position Y en pixels
     * @param width Largeur de l'ennemi en pixels
     * @param height Hauteur de l'ennemi en pixels
     */

    // NOUVEAU CONSTRUCTEUR avec texturePath
    public Enemy(float x, float y, float width, float height, String texturePath) {
        super(x, y, width, height);
        texture = new Texture(texturePath);
    }

    public Enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
        texture = new Texture("enemy.png");
    }

    /**
     * Met à jour l'état de l'ennemi.
     * <p>
     * Cette version d'ennemi est statique et ne nécessite pas de mise à jour.
     * </p>
     *
     * @param delta Temps écoulé depuis la dernière frame (non utilisé)
     */

    @Override
    public void update(float delta) {
        // Ennemi simple qui ne bouge pas
    }

    /**
     * Dessine l'ennemi à l'écran.
     *
     * @param batch Le SpriteBatch utilisé pour le rendu graphique
     */

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    /**
     * Libère les ressources graphiques de l'ennemi.
     */

    @Override
    public void dispose() {
        texture.dispose();
    }
}
