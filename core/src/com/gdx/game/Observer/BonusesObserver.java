package com.gdx.game.Observer;

import com.gdx.game.Globals;
import com.gdx.game.ToastAdapter;

public class BonusesObserver implements Observer {
    @Override
    public void update() {
        if (Globals.points == 1000)
            ToastAdapter.toastShort("Dodatkowe zycie");

    }
}
