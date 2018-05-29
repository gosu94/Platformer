package com.gdx.game.Memento;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.Components.BoundsComponent;
import com.gdx.game.Entity.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class State implements Serializable {

    Integer score;
    Integer lifes;

    List<Vector2> coinsPos = new ArrayList<>();
    List<Vector2> rocksPos = new ArrayList<>();
    List<Vector2> enemiesPos = new ArrayList<>();
    Vector2 playerPos;
    Vector2 cloverPos;

    public State() {
    }

    public State(List<Entity> entityList, Integer score, Integer lifes) {
        this.score = score;
        this.lifes = lifes;
        List<Entity> coins = new ArrayList<>();
        List<Entity> rocks = new ArrayList<>();
        List<Entity> enemies = new ArrayList<>();
        Entity player = null;
        Entity clover = null;
        for (Entity entity : entityList) {
            if (entity.getName() == null) continue;
            String name = entity.getName();
            switch (name) {
                case "Player":
                    player = entity;
                    break;
                case "Enemy":
                    enemies.add(entity);
                    break;
                case "Coin":
                    coins.add(entity);
                    break;
                case "Rock":
                    rocks.add(entity);
                    break;
                case "Clover":
                    clover = entity;
                    break;
            }
        }

        BoundsComponent boundsComp = (BoundsComponent) player.getComponent("BoundsComponent");
        playerPos = boundsComp.position;

        BoundsComponent cloverBoundsComp = (BoundsComponent) clover.getComponent("BoundsComponent");
        cloverPos = cloverBoundsComp.position;

        for (Entity coin : coins) {
            BoundsComponent boundsComponent = (BoundsComponent) coin.getComponent("BoundsComponent");
            coinsPos.add(boundsComponent.position);
        }
        for (Entity enemy : enemies) {
            BoundsComponent boundsComponent = (BoundsComponent) enemy.getComponent("BoundsComponent");
            enemiesPos.add(boundsComponent.position);
        }
        for (Entity rock : rocks) {
            BoundsComponent boundsComponent = (BoundsComponent) rock.getComponent("BoundsComponent");
            rocksPos.add(boundsComponent.position);
        }

    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


}
