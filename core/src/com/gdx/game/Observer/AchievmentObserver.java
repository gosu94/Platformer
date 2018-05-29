package com.gdx.game.Observer;

import com.gdx.game.Entity.Entity;
import com.gdx.game.Globals;
import com.gdx.game.Level;
import com.gdx.game.WorldRenderer;

public class AchievmentObserver implements Observer {

    int coinsCounter;

    public AchievmentObserver() {
        coinsCounter = 0;
        for (Entity entity : Level.entities) {
            if ("Coin".equals(entity.getName())) coinsCounter++;
        }
    }

    @Override
    public void update() {
        if (Globals.points == coinsCounter * 100)
            WorldRenderer.toast.toastShort("Achivment unclocked: collect all coins");

    }
}
