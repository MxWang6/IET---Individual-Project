package ie.tcd.mengxia;

import com.badlogic.gdx.graphics.Texture;

public class Pipeline implements GameObject {
    private final Texture texture;
    private final FlappyBirdGame game;
    private final float moveVelocity;

    private int x;
    private int y;

    public Pipeline(FlappyBirdGame game, Texture texture) {
        this.game = game;
        this.texture = texture;
        moveVelocity = game.getScreenWidth() / 5; //  /s

        x = game.getScreenWidth();
        y = game.getScreenHeight()/6;
    }

    @Override
    public void render(float delta) {
        updatePosition(delta);

        game.getBatch().begin();
        game.getBatch().draw(texture, x, y, texture.getWidth(), game.getScreenHeight());
        game.getBatch().end();
    }

    private void updatePosition(float delta) {
        switch (game.getStatus()) {
            case GAME_RUNNING:
                x -= moveVelocity * delta;
                break;
            case GAME_READY:
            case GAME_OVER:
                // TODO handling other game status.
                break;
            default:
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return texture.getWidth();
    }
}
