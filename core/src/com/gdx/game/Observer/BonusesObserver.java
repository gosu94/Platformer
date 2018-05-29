package com.gdx.game.Observer;

import com.gdx.game.Constants;
import com.gdx.game.Globals;
import com.gdx.game.WorldRenderer;

public class BonusesObserver implements Observer {
    @Override
    public void update() {
        if (Globals.points % 1000 == 0 && Globals.points > 0 && Globals.lives < Constants.LIVES_START) {
            WorldRenderer.toast.toastShort("You have earned additional life");
            Globals.lives++;
        }

    }
}
