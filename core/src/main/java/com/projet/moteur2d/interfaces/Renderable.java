package com.projet.moteur2d.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interface définissant le comportement d'une entité qui peut être affichée.
 * <p>
 * Toute entité visible à l'écran doit implémenter cette interface.
 * </p>
 *
 * @author Ngoc Mai Nguyen
 * @version 1.0
 */

public interface Renderable {
    /**
     * Dessine l'entité à l'écran.
     *
     * @param batch Le SpriteBatch utilisé pour le rendu graphique
     */
    void render(SpriteBatch batch);
}
