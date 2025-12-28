package com.projet.moteur2d.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.projet.moteur2d.engine.GameEngine;
import com.projet.moteur2d.engine.MapLoader;
import com.projet.moteur2d.world.GameMap;

/**
 * Écran principal du jeu contenant la logique d'affichage et de boucle de jeu.
 * <p>
 * Cette classe gère le rendu de la carte, des entités, et la caméra qui suit le joueur.
 * </p>
 *
 * @author Ngoc Mai Nguyen
 * @version 1.0
 */

public class GameScreen implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;

    private GameMap gameMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private MapLoader mapLoader;
    private GameEngine gameEngine;

    /**
     * Initialise l'écran de jeu.
     * <p>
     * Charge la carte, les entités, et prépare les composants de rendu.
     * </p>
     */

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(2);

        gameMap = new GameMap("maps/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(gameMap.getMap());

        mapLoader = new MapLoader();
        mapLoader.loadFromMap(gameMap.getMap(), gameMap);

        gameEngine = new GameEngine();
        gameEngine.initialize(mapLoader.getPlayer(), mapLoader.getEnemies());
    }

    /**
     * Méthode principale de rendu appelée à chaque frame.
     *
     * @param delta Temps écoulé depuis la dernière frame en secondes
     */

    @Override
    public void render(float delta) {
        // Nettoyer l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Mettre à jour la logique du jeu
        gameEngine.update(delta);

        // Positionner la caméra sur le joueur
        camera.position.set(
            gameEngine.getPlayer().getX() + gameEngine.getPlayer().getWidth() / 2f,
            gameEngine.getPlayer().getY() + gameEngine.getPlayer().getHeight() / 2f,
            0
        );
        camera.update();


        // Rendre la carte
        mapRenderer.setView(camera);
        mapRenderer.render();

        // Rendre les entités
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        gameEngine.getPlayer().render(batch);

        for (com.projet.moteur2d.entities.Enemy enemy : gameEngine.getEnemies()) {
            enemy.render(batch);
        }

        // Afficher Game Over si nécessaire
        if (gameEngine.isGameOver()) {
            font.draw(batch, "GAME OVER",
                gameEngine.getPlayer().getX() - 50,
                gameEngine.getPlayer().getY() + 50);
        }

        batch.end();
    }

    /**
     * Redimensionne la vue lorsque la fenêtre est redimensionnée.
     *
     * @param width Nouvelle largeur de la fenêtre
     * @param height Nouvelle hauteur de la fenêtre
     */

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    /**
     * Libère toutes les ressources de l'écran.
     * <p>
     * Doit être appelé lorsque l'écran n'est plus utilisé.
     * </p>
     */

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        mapRenderer.dispose();
        gameMap.dispose();
        gameEngine.getPlayer().dispose();
        for (com.projet.moteur2d.entities.Enemy enemy : gameEngine.getEnemies()) {
            enemy.dispose();
        }
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
