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
    String name;


    public Entity(String name) {
        this.name = name;
        componentList = new ArrayList<>();
        nameMap = new HashMap<>();
        id = counter;
        counter++;
        componentCounter = 0;
    }

    public void addComponent(Component component) {
        componentList.add(component);
        nameMap.put(component.getName(), componentCounter);
        componentCounter++;
    }

    public void removeComponent(String componentName) {
        for (Component component : componentList) {
            if (component.name.equals(componentName))
                componentList.remove(component);
        }
    }

    public Component getComponent(String componentName) {
        for (Component component : componentList) {
            if (component.name.equals(componentName))
                return component;
        }
        return null;
    }

}
