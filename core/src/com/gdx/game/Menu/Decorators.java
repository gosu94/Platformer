package com.gdx.game.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Decorators {

    public static class ActionCopy extends Action {
        Action action;

        protected Actor actor;

        protected Actor target;

        private Pool pool;


        public ActionCopy(Action actionToBeCopied) {
            this.actor = actionToBeCopied.getActor();
            this.target = actionToBeCopied.getTarget();
            this.pool = actionToBeCopied.getPool();
            this.action = actionToBeCopied;
        }

        @Override
        public boolean act(float delta) {
            return true;
        }

        @Override
        public void restart() {
            super.restart();
        }

        @Override
        public Actor getActor() {
            return super.getActor();
        }

        @Override
        public void setActor(Actor actor) {
            super.setActor(actor);
        }

        @Override
        public Actor getTarget() {
            return super.getTarget();
        }

        @Override
        public void setTarget(Actor target) {
            super.setTarget(target);
        }

        @Override
        public void reset() {
            super.reset();
        }

        @Override
        public Pool getPool() {
            return super.getPool();
        }

        @Override
        public void setPool(Pool pool) {
            super.setPool(pool);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


    public static class Shake extends ButtonDecorator {
        public Shake(Button buttonToBeDecorated) {
            super(buttonToBeDecorated);
            group.addActor(this);
            group.addAction(Actions.forever(new SequenceAction(
                    Actions.moveBy(10, 0, 0.5f),
                    Actions.moveBy(-10, 0, 0.5f))));

        }
    }

    public static class FadeOut extends ButtonDecorator {
        public FadeOut(Button buttonToBeDecorated) {
            super(buttonToBeDecorated);
            Group group2 = new Group();
            Array<Action> actions = buttonToBeDecorated.group.getActions();
            System.out.println("Size = " + actions.size);
            group.addActor(buttonToBeDecorated);
            group.addActor(this);
            for (Action action : actions) {
                System.out.println("Tu jest akcja: " + action);
                group.addAction(action);
            }

            group.addAction(Actions.forever(new SequenceAction(Actions.fadeOut(3), Actions.fadeIn(3))));

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
