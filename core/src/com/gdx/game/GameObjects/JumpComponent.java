package com.gdx.game.GameObjects;

public class JumpComponent extends Component {
    private final float JUMP_TIME_MAX = 0.3f;
    private final float JUMP_TIME_MIN = 0.1f;
    private final float JUMP_TIME_OFFSET_FLYING = JUMP_TIME_MAX - 0.018f;
    public Player.JUMP_STATE jumpState;
    private float timeJumping;

    public JumpComponent() {
        super("JumpComponent");
    }
}
