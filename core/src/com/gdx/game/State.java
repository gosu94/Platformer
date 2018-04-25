package com.gdx.game;

import com.gdx.game.Entity.Entity;

import java.util.List;

public class State {

    Integer score;
    List<Entity> entityList;

    public State() {

    }

    public State(List<Entity> entityList, Integer score) {
        this.entityList = entityList;
        this.score = score;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
