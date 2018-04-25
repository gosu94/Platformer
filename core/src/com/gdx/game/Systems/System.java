package com.gdx.game.Systems;

import com.gdx.game.Components.Component;
import com.gdx.game.Entity.Entity;

import java.util.List;

abstract public class System {
    public List<Entity> entityList;


    System(List<Entity> entityList) {
        this.entityList = entityList;
    }

    boolean containsComponent(final List<Component> list, final String name) {
        return list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }

    Component getComponentOfEntity(Entity entity, String componentName) {
        return entity.componentList.get(entity.nameMap.get(componentName));
    }

    public void removeIfNeccesarry(Entity entity) {
        if (entity.toRemove) {
            entityList.remove(entity);
        }

    }


}
