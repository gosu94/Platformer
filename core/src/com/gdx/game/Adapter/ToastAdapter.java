package com.gdx.game.Adapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Constants;
import com.gdx.game.GameScreens.MenuScreen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ToastAdapter implements ToastWindow {

    private static final List<Toast> toasts = new LinkedList<Toast>();
    private static Toast.ToastFactory toastFactory;

    public ToastAdapter() {
        toastFactory = new Toast.ToastFactory.Builder()
                .font(MenuScreen.font)
                .positionY(Constants.VIEWPORT_GUI_HEIGHT - 5)
                .build();
    }

    public void toastLong(String text) {
        toasts.add(toastFactory.create(text, Toast.Length.LONG));
    }

    public void toastShort(String text) {
        toasts.add(toastFactory.create(text, Toast.Length.SHORT));
    }

    public void render(SpriteBatch batch) {
        Iterator<Toast> it = toasts.iterator();
        while (it.hasNext()) {
            Toast t = it.next();
            if (!t.render(Gdx.graphics.getDeltaTime(), batch)) {
                it.remove(); // toast finished -> remove
            } else {
                break; // first toast still active, break the loop
            }
        }
    }
}
