package com.projet.moteur2d.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projet.moteur2d.utils.Constants;
import com.projet.moteur2d.world.GameMap;

/**
 * Classe représentant le joueur contrôlable dans le jeu.
 * <p>
 * Le joueur peut être déplacé avec les touches ZQSD et entre en collision
 * avec les murs de la carte et les ennemis.
 * </p>
 *
 * @author [Ton nom]
 * @version 1.0
 * @see Entity
 */

public class Player extends Entity {
    private Texture texture;
    private GameMap gameMap;

    /**
     * Construit un nouveau joueur à la position spécifiée.
     *
     * @param x Position X initiale en pixels
     * @param y Position Y initiale en pixels
     * @param gameMap La carte du jeu pour vérifier les collisions
     */

    public Player(float x, float y, GameMap gameMap) {
        super(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.gameMap = gameMap;
        texture = new Texture("player/player.png");
    }

    /**
     * Met à jour l'état du joueur.
     * <p>
     * Gère le déplacement en fonction des touches pressées et vérifie
     * les collisions avec la carte avant de déplacer le joueur.
     * </p>
     *
     * @param delta Temps écoulé depuis la dernière frame en secondes
     */

    @Override
    public void update(float delta) {
        float speed = Constants.PLAYER_SPEED * delta;
        float newX = x;
        float newY = y;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) newY += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) newY -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) newX -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) newX += speed;

        if (!gameMap.collidesWithMap(newX, newY, width, height)) {
            x = newX;
            y = newY;
        }
    }



    /**
     * Dessine le joueur à l'écran.
     *
     * @param batch Le SpriteBatch utilisé pour le rendu graphique
     */

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    /**
     * Libère les ressources graphiques du joueur.
     */

    @Override
    public void dispose() {
        texture.dispose();
    }
}
