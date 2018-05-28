package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.gdx.game.Components.BoundsComponent;
import com.gdx.game.Entity.Entity;
import com.gdx.game.Interpreter.LevelParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Memento implements Serializable {

    private static final String TAG = WorldController.class.getName();
    State state;
    String fileName;


    public Memento(State state, String fileName) {
        this.state = state;
        this.fileName = fileName;
        Json json = new Json();
        HashMap<String, Object> saved = new HashMap<String, Object>();


        try {

            FileOutputStream fileOut = new FileOutputStream(fileName + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(state);
            out.close();
            fileOut.close();
            Gdx.app.log(TAG, "Level saved");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromMemento(Memento memento) {
        State loadedState = new State();

        try {
            FileInputStream fileIn = new FileInputStream(memento.fileName + ".dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadedState = (State) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Entity> entityList = new ArrayList<>();

        Entity player = new Entity("Player");
        LevelParser.PlayerPixel.addComponents(player, loadedState.playerPos.x, loadedState.playerPos.y);
        entityList.add(player);
        Level.playerEntity = player;
        Level.playerBounds = (BoundsComponent) player.getComponent("BoundsComponent");
        for (Vector2 coinPos : loadedState.coinsPos) {
            Entity coin = new Entity("Coin");
            LevelParser.CoinPixel.addComponents(coin, coinPos.x, coinPos.y);
            entityList.add(coin);
        }
        for (Vector2 rockPos : loadedState.rocksPos) {
            Entity rock = new Entity("Rock");
            LevelParser.RockPixel.addComponents(rock, rockPos.x, rockPos.y);
            entityList.add(rock);
        }
        for (Vector2 enemyPos : loadedState.enemiesPos) {
            Entity enemy = new Entity("Enemy");
            LevelParser.EnemyPixel.addComponents(enemy, enemyPos.x, enemyPos.y);
            entityList.add(enemy);
        }
        Level.entities.clear();
        for (Entity entity : entityList) {
            Level.entities.add(entity);
        }
        WorldController.cameraHandler.setTarget(Level.playerBounds);
        WorldController.cameraHandler.setPosition(0, 3);
        Globals.points = loadedState.score;
        Gdx.app.log(TAG, "Level successfully loaded");
    }


}
