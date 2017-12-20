package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Memento {

    private static final String TAG = WorldController.class.getName();

    static void save(Player player, Array<Enemy> enemies, Array<Rock> rocks, Array<Coin> coins) {

        HashMap<String, Object> saved = new HashMap<String, Object>();
        Vector2 playerPos = player.position;
        List<Vector2> enemiesPos = new ArrayList<Vector2>();
        List<Vector2> rocksPos = new ArrayList<Vector2>();
        List<Vector2> coinsPos = new ArrayList<Vector2>();

        for (Enemy enemy : enemies)
            enemiesPos.add(enemy.position);
        for (Rock rock : rocks)
            rocksPos.add(rock.position);
        for (Coin coin : coins)
            coinsPos.add(coin.position);

        saved.put("Player", playerPos);
        saved.put("Enemies", enemiesPos);
        saved.put("Rocks", rocksPos);
        saved.put("Coins", coinsPos);

        try {
            FileOutputStream fos = new FileOutputStream("saved.obj");
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

    static void load() {

        try {
            FileInputStream fis = new FileInputStream("saved.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            HashMap<String, Object> retrived = (HashMap<String, Object>) ois.readObject();
            fis.close();
            Gdx.app.log(TAG, "Level Loaded");

            System.out.println(retrived.get("Coins"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
