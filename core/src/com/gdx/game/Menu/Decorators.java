package com.gdx.game.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class Decorators {

    public static class ActionCopy extends Action {
        Action action;

        public ActionCopy(Action actionToBeCopied) {
            this.action = actionToBeCopied;
        }

        @Override
        public boolean act(float delta) {
            action.act(delta);
            return true;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
    public static class Shake extends ButtonDecorator {
        public Shake(Button buttonToBeDecorated) {
            super(buttonToBeDecorated);

            addAction(Actions.forever(new SequenceAction(
                    Actions.moveBy(10, 0, 0.5f),
                    Actions.moveBy(-10, 0, 0.5f))));

        }
    }

    public static class FadeOut extends ButtonDecorator {
        public FadeOut(Button buttonToBeDecorated) {
            super(buttonToBeDecorated);
            Array<Action> actions = buttonToBeDecorated.getActions();
            List<Action> actionCopies = new ArrayList<>();
            for (Action action : actions) {
                ActionCopy actionCopy = new ActionCopy(action);
                System.out.println(actionCopy);
                addAction(actionCopy);
            }
            //addAction(Actions.forever(new SequenceAction(Actions.fadeOut(3), Actions.fadeIn(3))));

        }
    }

    public static class RedHover extends ButtonDecorator {
        public RedHover(Button buttonToBeDecorated) {
            super(buttonToBeDecorated);
            clickListener = new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);
                    setColor(new Color(255, 0, 0, 100));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);
                    setColor(defaultColor);
                }
            };
            addListener(clickListener);
        }


    }

    public static class BlueHover extends ButtonDecorator {
        public BlueHover(Button buttonToBeDecorated) {
            super(buttonToBeDecorated);
            addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);
                    setColor(new Color(0, 0, 255, 100));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);
                    setColor(defaultColor);
                }
            });
        }

    }


}
