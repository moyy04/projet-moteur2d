package com.projet.moteur2d.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.projet.moteur2d.utils.Constants;

/**
 * Gère la carte du jeu et les collisions tile-based.
 *
 * @author Ahlame Lamkiki
 * @version 2.0
 */
public class GameMap {

    private final TiledMap map;
    private final TiledMapTileLayer collisionLayer;

    /**
     * Charge la carte Tiled (.tmx)
     */
    public GameMap(String path) {
        map = new TmxMapLoader().load(path);

        collisionLayer = (TiledMapTileLayer) map.getLayers().get("collision");

        if (collisionLayer == null) {
            throw new RuntimeException(
                "❌ La couche 'collision' est introuvable dans la map Tiled !");
        }
    }

    /**
     * Détecte une collision entre une entité et la map.
     *
     * @param x position X en pixels
     * @param y position Y en pixels
     * @param width largeur de l'entité
     * @param height hauteur de l'entité
     */
    public boolean collidesWithMap(float x, float y, float width, float height) {
        int tileSize = Constants.TILE_SIZE;

        int leftTile   = (int) (x / tileSize);
        int rightTile  = (int) ((x + width) / tileSize);
        int bottomTile = (int) (y / tileSize);
        int topTile    = (int) ((y + height) / tileSize);

        return isBlocked(leftTile, bottomTile)
            || isBlocked(leftTile, topTile)
            || isBlocked(rightTile, bottomTile)
            || isBlocked(rightTile, topTile);
    }

    /**
     * Vérifie si une tuile est bloquante
     */
    private boolean isBlocked(int tileX, int tileY) {
        // En dehors de la carte = mur
        if (tileX < 0 || tileY < 0
            || tileX >= collisionLayer.getWidth()
            || tileY >= collisionLayer.getHeight()) {
            return true;
        }

        TiledMapTileLayer.Cell cell = collisionLayer.getCell(tileX, tileY);
        return cell != null && cell.getTile() != null;
    }

    /**
     * Retourne la map Tiled
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * Libère la mémoire
     */
    public void dispose() {
        map.dispose();
    }
}
