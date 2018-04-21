package com.gdx.game.Components;

import com.gdx.game.Constants;

public class JumpComponent extends Component {
    public final float JUMP_TIME_MAX = 0.3f;
    public final float JUMP_TIME_MIN = 0.1f;
    public final float JUMP_TIME_OFFSET_FLYING = JUMP_TIME_MAX - 0.018f;
    public Constants.JUMP_STATE jumpState;
    public float timeJumping;
    public boolean jumpKeyPressed;


    public JumpComponent() {
        super("JumpComponent");
        jumpKeyPressed = false;
        jumpState = Constants.JUMP_STATE.FALLING;
        timeJumping = 0;
    }
}
