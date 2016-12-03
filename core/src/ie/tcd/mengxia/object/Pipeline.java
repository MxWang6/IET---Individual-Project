package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import ie.tcd.mengxia.FlappyBirdGame;

public class Pipeline implements Drawable {
    private static final Texture pipelineTop = new Texture(Gdx.files.internal("tubetop.png"));
    private static final Texture pipelineBottom = new Texture(Gdx.files.internal("tubebottom.png"));

    private final FlappyBirdGame game;
    private final float moveVelocity;
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

        top = new Rectangle(x, y + random.nextInt(RANDOM) + GAP_HEIGHT + 150, pipelineTop.getWidth() * 2, pipelineBottom.getHeight() * 2);

        bottom = new Rectangle(x, top.getY() - GAP_HEIGHT - pipelineBottom.getHeight() * 2, pipelineTop.getWidth() * 2, pipelineBottom.getHeight() * 2);
    }

    @Override
    public void draw() {
        game.getBatch().begin();
        game.getBatch().draw(pipelineTop, top.getX(), top.getY(), top.getWidth(), top.getHeight());
        game.getBatch().draw(pipelineBottom, top.getX(), bottom.getY(), bottom.getWidth(), bottom.getHeight());
        game.getBatch().end();
    }

    @Override
    public void update(float delta) {
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
