package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Pipeline implements GameObject {
    private final FlappyBirdGame game;
    private final float moveVelocity;
    private static final Texture bambooTop = new Texture(Gdx.files.internal("bambooTop.png"));
    private static final Texture bambooBottom = new Texture(Gdx.files.internal("bambooBottom.png"));
    private final Rectangle top;
    private final Rectangle bottom;
    private static Random random = new Random();
    private static final int GAP_HEIGHT = 200;
    private static final int RANDOM = 500;
    private boolean justPassed = false;

    public Pipeline(FlappyBirdGame game) {
        this.game = game;
        moveVelocity = game.getScreenWidth() / 5; //  /s

        float x = game.getScreenWidth();
        float y = game.getScreenHeight()/6;

        top = new Rectangle(x, y + random.nextInt(RANDOM) + GAP_HEIGHT + 150, bambooTop.getWidth() * 1.5f, bambooBottom.getHeight() * 1.5f);

        bottom = new Rectangle(x, top.getY() - GAP_HEIGHT - bambooBottom.getHeight() * 1.5f, bambooTop.getWidth() * 1.5f, bambooBottom.getHeight() * 1.5f);
    }

    @Override
    public void render(float delta) {
        updatePosition(delta);

        game.getBatch().begin();
        // game.getBatch().draw(texture, x, y, texture.getWidth(), game.getScreenHeight());
        game.getBatch().draw(bambooTop, top.getX(), top.getY(), top.getWidth(), top.getHeight());
        game.getBatch().draw(bambooBottom, top.getX(), bottom.getY(), bottom.getWidth(), bottom.getHeight());
        game.getBatch().end();
    }

    private void updatePosition(float delta) {
        switch (game.getStatus()) {
            case GAME_RUNNING:
                top.x -= moveVelocity * delta;
                bottom.x -= moveVelocity * delta;
                break;
            case GAME_READY:
            case GAME_OVER:
                // TODO handling other game status.
                break;
            default:
                break;
        }
    }

    public boolean collides(Rectangle boundsBird) {
        final boolean isColliding = boundsBird.overlaps(top) || boundsBird.overlaps(bottom);
        return isColliding;
    }

    public boolean justPassed(Rectangle birdShape) {
        if (!justPassed && birdShape.x > top.x + top.width) {
                return justPassed = true;
        }

        return false;
    }

    public float getX() {
        return top.x;
    }

    public float getWidth() {
        return top.getWidth();
    }
}
