package com.gdx.game.Memento;

import com.badlogic.gdx.Gdx;
import com.gdx.game.WorldController;

import java.io.*;
import java.util.List;

public class Originator {

    private static final String TAG = WorldController.class.getName();

    public static Memento saveToMemento(State state, String fileName) {
        return new Memento(state, fileName);
    }

    public static void loadFromMemento(Memento memento) {
        memento.loadFromMemento(memento);
    }

    public static void saveMementos(List<Memento> mementos) {

        try {
            FileOutputStream fos = new FileOutputStream("mementos.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mementos);
            Gdx.app.log(TAG, "Mementos saved");
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Memento> loadMementos() {
        try {
            FileInputStream fis = new FileInputStream("mementos.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<Memento> retrieved = (List<Memento>) ois.readObject();
            fis.close();
            Gdx.app.log(TAG, "Mementos Loaded");

            return retrieved;

        } catch (FileNotFoundException e) {
            System.out.println("Mementos file not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
