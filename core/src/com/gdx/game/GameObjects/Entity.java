package com.gdx.game.GameObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entity {
    static int counter;
    long id;
    List<Component> componentList;
    int componentCounter;
    HashMap<String, Integer> nameMap;


    Entity() {
        componentList = new ArrayList<>();
        nameMap = new HashMap<>();
        id = counter;
        counter++;
        componentCounter = 0;
    }

    void addComponent(Component component) {
        componentList.add(component);
        nameMap.put(component.getName(), componentCounter);
        componentCounter++;
    }

    void removeComponent(String componentName) {
        for (Component component : componentList) {
            if (component.name.equals(componentName))
                componentList.remove(component);
        }
    }

}
