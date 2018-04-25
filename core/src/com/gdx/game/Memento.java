package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.gdx.game.Entity.Entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

public class Memento {

    private static final String TAG = WorldController.class.getName();
    State state;
    String fileName;
    Kryo kryo;

    public Memento(State state, String fileName) {
        this.state = state;
        this.fileName = fileName;
        kryo = new Kryo();
        HashMap<String, Object> saved = new HashMap<String, Object>();


        try {

            Output output = new Output(new FileOutputStream(fileName + ".bin"));
            kryo.writeObject(output, state);
            output.close();

            Gdx.app.log(TAG, "Level saved");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadFromMemento(Memento memento) {

        try {
            Input input = new Input(new FileInputStream(memento.fileName + ".bin"));
            State state = kryo.readObject(input, State.class);
            input.close();
            for (Entity entity : state.entityList) {
                System.out.println(entity.getName());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
