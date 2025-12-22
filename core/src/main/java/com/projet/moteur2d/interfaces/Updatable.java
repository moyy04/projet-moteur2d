package com.projet.moteur2d.interfaces;

/**
 * Interface définissant le comportement d'une entité qui peut être mise à jour.
 * <p>
 * Toute entité qui évolue dans le temps doit implémenter cette interface.
 * </p>
 *
 * @author Ngoc Mai Nguyen
 * @version 1.0
 */

public interface Updatable {
    /**
     * Met à jour l'état de l'entité.
     *
     * @param deltaTime Temps écoulé depuis la dernière mise à jour en secondes
     */
    void update(float deltaTime);
}
