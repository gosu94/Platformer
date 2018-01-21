package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Memento {

    private static final String TAG = WorldController.class.getName();
    State state;
    String fileName;

    Memento(State state, String fileName) {
        this.state = state;
        this.fileName = fileName;

        HashMap<String, Object> saved = new HashMap<String, Object>();
        Vector2 playerPos = state.player.position;

        List<Vector2> enemiesPos = new ArrayList<Vector2>();
        List<Vector2> rocksPos = new ArrayList<Vector2>();
        List<Vector2> coinsPos = new ArrayList<Vector2>();
        List<Boolean> coinsCollected = new ArrayList<Boolean>();

        for (Enemy enemy : state.enemies)
            enemiesPos.add(enemy.position);
        for (Rock rock : state.rocks)
            rocksPos.add(rock.position);
        for (Coin coin : state.coins) {
            coinsPos.add(coin.position);
            coinsCollected.add(coin.collected);
        }

        saved.put("Player", playerPos);
        saved.put("Enemies", enemiesPos);
        saved.put("Rocks", rocksPos);
        saved.put("Coins", coinsPos);
        saved.put("CoinsCollected", coinsCollected);
        saved.put("Score", state.score);

        try {
            FileOutputStream fos = new FileOutputStream(fileName + ".obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(saved);
            Gdx.app.log(TAG, "Level saved");
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadFromMemento(Memento memento) {

        try {
            FileInputStream fis = new FileInputStream(memento.fileName + ".obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            HashMap<String, Object> retrieved = (HashMap<String, Object>) ois.readObject();
            fis.close();
            Gdx.app.log(TAG, "Level Loaded");

            //System.out.println(retrieved.get("Coins"));
            List<Vector2> coinsPos = (List<Vector2>) retrieved.get("Coins");
            List<Vector2> enemiesPos = (List<Vector2>) retrieved.get("Enemies");
            List<Vector2> rocksPos = (List<Vector2>) retrieved.get("Rocks");
            List<Boolean> coinsCollected = (List<Boolean>) retrieved.get("CoinsCollected");
            Vector2 playerPos = (Vector2) retrieved.get("Player");


            for (int i = 0; i < Level.rocks.size; i++)
                Level.rocks.get(i).setPosition(rocksPos.get(i));

            for (int i = 0; i < Level.coins.size; i++)
                Level.coins.get(i).collected = coinsCollected.get(i);

            for (int i = 0; i < Level.coins.size; i++)
                Level.coins.get(i).setPosition(coinsPos.get(i));

            for (int i = 0; i < Level.enemies.size; i++)
                Level.enemies.get(i).setPosition(enemiesPos.get(i));

            Level.player.setPosition(playerPos);
            WorldController.score = (Integer) retrieved.get("Score");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
