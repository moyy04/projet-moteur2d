package com.projet.moteur2d.engine;

import com.projet.moteur2d.entities.Player;
import com.projet.moteur2d.entities.Enemy;
import java.util.List;

/**
 * Moteur principal du jeu gérant la logique métier.
 * <p>
 * Cette classe orchestre les mises à jour des entités et gère
 * les règles du jeu (collisions, état de jeu).
 * </p>
 *
 * @author Ahlame Lamkiki
 * @version 1.0
 */
public class GameEngine {
    private Player player;
    private List<Enemy> enemies;
    private boolean gameOver;

    /**
     * Initialise le moteur de jeu avec les entités chargées.
     *
     * @param player Le joueur à contrôler
     * @param enemies La liste des ennemis présents dans le niveau
     */
    public void initialize(Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.gameOver = false;
    }

    /**
     * Met à jour l'état du jeu.
     * <p>
     * Appelle les mises à jour des entités et vérifie les conditions
     * de fin de jeu (collisions avec ennemis).
     * </p>
     *
     * @param delta Temps écoulé depuis la dernière frame en secondes
     */
    public void update(float delta) {
        if (gameOver) return;

        // Mettre à jour le joueur
        player.update(delta);

        // Vérifier collisions avec les ennemis
        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                gameOver = true;
                break;
            }
        }
    }

    /**
     * Vérifie si la partie est terminée.
     *
     * @return true si le joueur a perdu, false sinon
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Récupère le joueur actuel.
     *
     * @return L'instance du joueur
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Récupère la liste des ennemis.
     *
     * @return Liste des ennemis présents dans le niveau
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }
}
