package com.gdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.Components.BoundsComponent;

public class CameraHandler {

    private static final String TAG = CameraHandler.class.getName();
    private final float MAX_ZOOM_IN = 0.25f;
    private final float MAX_ZOOM_OUT = 10.0f;
    private Vector2 position;
    private float zoom;
    private BoundsComponent target;

    CameraHandler() {
        position = new Vector2();
        zoom = 1.2f;
    }

    void update(float deltaTime) {
        if (!hasTarget()) return;
        position.x = target.position.x + target.origin.x;
        //position.y = target.position.y + target.origin.y;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    Vector2 getPosition() {
        return position;
    }

    void addZoom(float amount) {
        setZoom(zoom + amount);
    }

    void setZoom(float zoom) {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }

    public float getZoom() {
        return zoom;
    }

    public BoundsComponent getTarget() {
        return target;
    }

    public void setTarget(BoundsComponent target) {
        this.target = target;
    }

    public boolean hasTarget() {
        return target != null;
    }

    public boolean hasTarget(BoundsComponent target) {
        return hasTarget() && this.target.equals(target);
    }

    void applyTo(OrthographicCamera camera) {
        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.zoom = zoom;
        camera.update();
    }
}
