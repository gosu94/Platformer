package com.gdx.game;

import com.badlogic.gdx.utils.Array;
import com.gdx.game.GameObjects.Coin;
import com.gdx.game.GameObjects.Enemy;
import com.gdx.game.GameObjects.Player;
import com.gdx.game.GameObjects.Rock;

public class State {
    Player player;
    Array<Enemy> enemies;
    Array<Rock> rocks;
    Array<Coin> coins;
    Integer score;

    public State(Player player, Array<Enemy> enemies, Array<Rock> rocks, Array<Coin> coins, Integer score) {
        this.player = player;
        this.enemies = enemies;
        this.rocks = rocks;
        this.coins = coins;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Array<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Array<Rock> getRocks() {
        return rocks;
    }

    public void setRocks(Array<Rock> rocks) {
        this.rocks = rocks;
    }

    public Array<Coin> getCoins() {
        return coins;
    }

    public void setCoins(Array<Coin> coins) {
        this.coins = coins;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
