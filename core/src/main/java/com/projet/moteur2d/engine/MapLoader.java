package com.projet.moteur2d.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
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
 * type = player | enemy
 * enemy_type = slime | goblin | skeleton
 *
 * @author Ahlame Lamkiki
 */
public class MapLoader {

    private Player player;
    private List<Enemy> enemies;

    public void loadFromMap(TiledMap map, GameMap gameMap) {
        enemies = new ArrayList<>();

        MapLayer entityLayer = map.getLayers().get("entities");

        if (entityLayer == null) {
            Gdx.app.error("MapLoader", "Layer 'entities' introuvable");
            return;
        }

        MapObjects objects = entityLayer.getObjects();

        for (MapObject object : objects) {

            if (!(object instanceof RectangleMapObject)) {
                continue;
            }

            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            String type = object.getProperties().get("type", String.class);

            if (type == null) {
                Gdx.app.log("MapLoader", "Objet ignoré (pas de type)");
                continue;
            }

            switch (type.toLowerCase()) {

                case "player":
                    player = new Player(rect.x, rect.y, gameMap);
                    break;

                case "enemy":
                    enemies.add(createEnemy(rect, object));
                    break;
            }
        }

        Gdx.app.log("MapLoader",
            "Chargé : " + enemies.size() + " ennemi(s), "
                + (player != null ? "1 joueur" : "0 joueur"));
    }

    private Enemy createEnemy(Rectangle rect, MapObject object) {

        // Lire le type d’ennemi (slime par défaut)
        String enemyType = object.getProperties()
            .get("enemy_type", String.class);

        if (enemyType == null) {
            enemyType = "slime";
        }

        String texturePath = "enemies/" + enemyType + ".png";

        if (!Gdx.files.internal(texturePath).exists()) {
            Gdx.app.error("MapLoader",
                "Texture introuvable : " + texturePath + ", fallback slime");
            texturePath = "enemies/slime.png";
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
