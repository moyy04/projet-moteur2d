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
 * @author Ngoc Mai Nguyen
 * @version 1.1
 * @see Entity
 */
public class Enemy extends Entity {
    private final Texture texture;

    /**
     * Construit un nouvel ennemi.
     *
     * @param x Position X en pixels
     * @param y Position Y en pixels
     * @param width Largeur de l'ennemi en pixels
     * @param height Hauteur de l'ennemi en pixels
     * @param texturePath Chemin vers la texture
     */
    public Enemy(float x, float y, float width, float height, String texturePath) {
        super(x, y, width, height);
        texture = new Texture(texturePath);

        // 🔑 Si la taille n'est pas définie dans Tiled
        if (this.width <= 0) this.width = texture.getWidth();
        if (this.height <= 0) this.height = texture.getHeight();
    }

    @Override
    public void update(float delta) {
        // Ennemi simple qui ne bouge pas
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
