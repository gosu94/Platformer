package com.gdx.game;

import com.gdx.game.Components.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entity {
    public static int counter;
    public long id;
    public List<Component> componentList;
    public int componentCounter;
    public HashMap<String, Integer> nameMap;
    public boolean toRemove;

    public String name;


    public Entity(String name) {
        this.name = name;
        componentList = new ArrayList<>();
        nameMap = new HashMap<>();
        id = counter;
        counter++;
        componentCounter = 0;
        toRemove = false;
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

    public boolean containsComponent(String componentName) {
        for (Component component : componentList) {
            if (component.name.equals(componentName))
                return true;
        }
        return false;
    }

    public void remove() {
        toRemove = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
