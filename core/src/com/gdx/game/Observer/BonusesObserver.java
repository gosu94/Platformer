package com.gdx.game.Observer;

import com.gdx.game.Globals;
import com.gdx.game.WorldRenderer;

public class BonusesObserver implements Observer {
    @Override
    public void update() {
        if (Globals.points == 1000)
            WorldRenderer.toast.toastShort("Dodatkowe zycie");

    }
}
