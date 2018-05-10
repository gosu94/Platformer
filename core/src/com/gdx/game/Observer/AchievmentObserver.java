package com.gdx.game.Observer;

import com.gdx.game.Globals;
import com.gdx.game.WorldRenderer;

public class AchievmentObserver implements Observer {
    @Override
    public void update() {
        if (Globals.points == 1500)
            WorldRenderer.toast.toastShort("Achivment unclocked: collect all coins");

    }
}
