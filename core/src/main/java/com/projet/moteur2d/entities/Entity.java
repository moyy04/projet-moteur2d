package com.projet.moteur2d.entities;

import com.projet.moteur2d.interfaces.Updatable;
import com.projet.moteur2d.interfaces.Renderable;

/**
 * Classe abstraite représentant une entité générique dans le jeu.
 * <p>
 * Cette classe sert de base pour toutes les entités du jeu (joueur, ennemis).
 * Elle définit les propriétés communes (position, taille) et méthodes abstraites
 * que toutes les entités doivent implémenter.
 * </p>
 *
 * @author Ngoc Mai Nguyen
 * @version 1.0
 * @see Player
 * @see Enemy
 */

public abstract class Entity implements Updatable, Renderable {
    protected float x, y;
    protected float width, height;

    /**
     * Constructeur d'une entité.
     *
     * @param x Position horizontale initiale en pixels
     * @param y Position verticale initiale en pixels
     * @param width Largeur de l'entité en pixels
     * @param height Hauteur de l'entité en pixels
     */

    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }

    /**
     * Vérifie si cette entité entre en collision avec une autre entité.
     * <p>
     * Utilise une détection de collision par rectangles alignés aux axes (AABB).
     * </p>
     *
     * @param other L'autre entité à tester
     * @return true si les deux entités se chevauchent, false sinon
     */

    public boolean collidesWith(Entity other) {
        return x < other.x + other.width &&
            x + width > other.x &&
            y < other.y + other.height &&
            y + height > other.y;
    }

    /**
     * Cette méthode doit être appelée lorsque l'entité n'est plus utilisé
     * </p>
     */
    public abstract void dispose();
}
