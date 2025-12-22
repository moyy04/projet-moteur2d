package com.projet.moteur2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.projet.moteur2d.screens.GameScreen;

/**
 * Classe principale du jeu qui initialise l'application LibGDX.
 * <p>
 * Cette classe est le point d'entrée du jeu et gère la navigation entre les écrans.
 * </p>
 *
 * @author Ngoc Mai Nguyen
 * @version 1.0
 */

public class MainGame extends Game {
    /**
     * Méthode appelée au démarrage du jeu.
     * <p>
     * Initialise l'application et affiche l'écran de jeu principal.
     * </p>
     */
    @Override
    public void create() {
        Gdx.app.log("MainGame", "Jeu démarré !");
        setScreen(new GameScreen());
    }
}
