package com.gdx.game.GameObjects;

import java.util.List;

abstract public class System {
    List<Entity> entityList;

    System(List<Entity> entityList) {
        this.entityList = entityList;
    }

    boolean containsComponent(final List<Component> list, final String name) {
        return list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }

    Component getComponentOfEntity(Entity entity, String componentName) {
        return entity.componentList.get(entity.nameMap.get(componentName));
    }


}
