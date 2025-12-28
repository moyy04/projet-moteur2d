package com.projet.moteur2d.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.projet.moteur2d.utils.Constants;

/**
 * Gère la carte du jeu et les collisions tile-based.
 * Une tuile présente dans la couche "collision" est considérée comme bloquante.
 *
 * @author Ahlame Lamkiki
 * @version 2.1
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
                "La couche 'collision' est introuvable dans la map Tiled !");
        }
    }

    /**
     * Détecte une collision entre une entité rectangulaire et la map.
     *
     * @param x position X en pixels
     * @param y position Y en pixels
     * @param width largeur de l'entité
     * @param height hauteur de l'entité
     * @return true si collision détectée
     */
    // Dans GameMap.java
    public boolean collidesWithMap(float x, float y, float width, float height) {
        int tileSize = Constants.TILE_SIZE;

        int startX = (int)(x / tileSize);
        int endX = (int)((x + width - 1) / tileSize);
        int startY = (int)(y / tileSize);
        int endY = (int)((y + height - 1) / tileSize);

        for (int tx = startX; tx <= endX; tx++) {
            for (int ty = startY; ty <= endY; ty++) {
                if (isBlocked(tx, ty)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Vérifie si une tuile est bloquante.
     * Hors de la carte = bloqué.
     */
    private boolean isBlocked(int tileX, int tileY) {
        // Hors de la map → mur

        if (tileX < 0 || tileY < 0
            || tileX >= collisionLayer.getWidth()
            || tileY >= collisionLayer.getHeight()) {
            return true;
        }

        TiledMapTileLayer.Cell cell = collisionLayer.getCell(tileX, tileY);

        // Une tuile présente dans le layer collision = mur
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
