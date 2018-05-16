package com.gdx.game.Entity;

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

    private String name;

    public Entity() {

    }

    public Entity(String name) {
        this.name = name;
        componentList = new ArrayList<Component>();
        nameMap = new HashMap<String, Integer>();
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
                component.setName("toRemove");
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

    public void reset() {
        componentList.clear();
        name = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
