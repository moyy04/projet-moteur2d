package com.projet.moteur2d.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.projet.moteur2d.entities.Player;
import com.projet.moteur2d.entities.Enemy;
import com.projet.moteur2d.world.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Charge les entités depuis une carte Tiled.
 */
public class MapLoader {

    private Player player;
    private List<Enemy> enemies;

    /**
     * Charge les entités depuis la carte Tiled.
     */
    public void loadFromMap(TiledMap map, GameMap gameMap) {
        enemies = new ArrayList<>();

        MapLayer entityLayer = map.getLayers().get("entities");
        if (entityLayer == null) {
            Gdx.app.error("MapLoader", "Calque 'entities' non trouvé");
            return;
        }

        MapObjects objects = entityLayer.getObjects();

        for (MapObject object : objects) {
            if (!(object instanceof RectangleMapObject)) continue;

            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            MapProperties props = object.getProperties();

            String type = props.get("type", String.class);
            if (type == null) continue;

            switch (type.toLowerCase()) {
                case "player":
                    player = createPlayer(rect, gameMap);
                    break;

                case "enemy":
                    enemies.add(createEnemy(rect, props));
                    break;
            }
        }

        Gdx.app.log(
            "MapLoader",
            "Chargé : " + enemies.size() + " ennemi(s)" +
                (player != null ? ", 1 joueur" : ", pas de joueur")
        );
    }

    /**
     * Crée le joueur.
     */
    private Player createPlayer(Rectangle rect, GameMap gameMap) {
        return new Player(rect.x, rect.y, gameMap);
    }

    /**
     * Crée un ennemi à partir des propriétés Tiled.
     */
    private Enemy createEnemy(Rectangle rect, MapProperties props) {

        // Lire le type d'ennemi
        String enemyType = props.get("enemy_type", String.class);
        if (enemyType == null) enemyType = "slime";

        // Chemin de la texture
        String texturePath = "enemies/" + enemyType + ".png";

        // Vérification
        if (!Gdx.files.internal(texturePath).exists()) {
            Gdx.app.error("MapLoader", "Texture non trouvée: " + texturePath);
            texturePath = "enemies/slime.png"; // fallback
        }

        return new Enemy(
            rect.x,
            rect.y,
            rect.width,
            rect.height,
            texturePath
        );
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
