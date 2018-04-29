package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.GameScreens.MenuScreen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ToastAdapter {

    private static final List<Toast> toasts = new LinkedList<Toast>();
    private static Toast.ToastFactory toastFactory;

    public ToastAdapter() {
        toastFactory = new Toast.ToastFactory.Builder()
                .font(MenuScreen.font)
                .positionY(520)
                .build();
    }

    public static void toastLong(String text) {
        toasts.add(toastFactory.create(text, Toast.Length.LONG));
    }

    public static void toastShort(String text) {
        toasts.add(toastFactory.create(text, Toast.Length.SHORT));
    }

    void render(SpriteBatch batch) {
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
