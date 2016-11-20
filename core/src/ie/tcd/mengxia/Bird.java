package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bird implements GameObject {
    private static final Texture texture = new Texture(Gdx.files.internal("bird.png")); // 136 * 96
    private static final Sound upSound = Gdx.audio.newSound(Gdx.files.internal("audio/up.wav"));

    private final FlappyBirdGame game;
    private final float dropVelocity;
    private final float upDistance;
    private final float topBoundary;
    private final float bottomBoundary;
    private final Rectangle birdShape;

    public Bird(FlappyBirdGame game) {
        this.game = game;
        dropVelocity = game.getScreenHeight() / 5;  // /s
        upDistance = 15;

        float birdWidth = texture.getWidth() / 2;
        float birdHeight = texture.getHeight() / 2;
        // initial bird position (x, y) with width and height in the view port coord.
        birdShape = new Rectangle();
        birdShape.setWidth(birdWidth);
        birdShape.setHeight(birdHeight);
        birdShape.setX(game.getScreenWidth()/2 - birdWidth/2);
        birdShape.setY(game.getScreenHeight()/2 -birdHeight/2);

        // top and bottom boundary that brid can fly
        topBoundary = game.getScreenHeight() - birdHeight;
        bottomBoundary = 180;
    }

    @Override
    public void render(float delta) {
        updatePosition(delta);

        game.getBatch().begin();
        game.getBatch().draw(texture, birdShape.getX(), birdShape.getY(), birdShape.getWidth(), birdShape.getHeight());
        game.getBatch().end();
    }

    public boolean hitBoundary() {
        return birdShape.getY() <= 180 || birdShape.getY() >= topBoundary;
    }

    private void updatePosition(float delta) {
        switch (game.getStatus()) {
            case GAME_RUNNING:
                if (Gdx.input.isTouched()) {
                    upSound.play();
                    birdShape.setY(Math.min(birdShape.getY() + upDistance, topBoundary));
                } else {
                    birdShape.setY(birdShape.getY() - dropVelocity * delta);
                }
                break;
            case GAME_OVER:
            case GAME_READY:
            default:
                // TODO: handling other status.
                break;
        }
    }
}
