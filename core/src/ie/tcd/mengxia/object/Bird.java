package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import ie.tcd.mengxia.Animation;
import ie.tcd.mengxia.FlappyBirdGame;

public class Bird implements Drawable {
    private static final Texture texture = new Texture(Gdx.files.internal("birdanimation.png")); // 136 * 96
    private static final Animation birdAnimation = new Animation(0.3f,
            new TextureRegion(texture, 0, 0, texture.getWidth() / 3, texture.getHeight()),
            new TextureRegion(texture, texture.getWidth() / 3, 0, texture.getWidth() / 3, texture.getHeight()),
            new TextureRegion(texture, texture.getWidth() / 3 * 2, 0, texture.getWidth() / 3, texture.getHeight()));

    private static final Sound upSound = Gdx.audio.newSound(Gdx.files.internal("audio/up.wav"));

    private final FlappyBirdGame game;
    private final float dropVelocity;
    private final float upDistance;
    private final float topBoundary;
    private final float bottomBoundary;
    private final Rectangle birdShape;
    private float stateTime = 0;

    public Bird(FlappyBirdGame game) {
        this.game = game;
        dropVelocity = game.getScreenHeight() / 5;  // /s
        upDistance = 15;

        // float birdWidth = texture.getWidth() / 2;
        // float birdHeight = texture.getHeight() / 2;
        float birdWidth = texture.getWidth() / 3 * 2;
        float birdHeight = texture.getHeight() * 2;
        // initial bird position (x, y) with width and height in the view port coord.
        birdShape = new Rectangle();
        birdShape.setWidth(birdWidth);
        birdShape.setHeight(birdHeight);
        birdShape.setX(game.getScreenWidth() / 2 - birdWidth / 2);
        birdShape.setY(game.getScreenHeight() / 2 - birdHeight / 2);

        // top and bottom boundary that brid can fly
        topBoundary = game.getScreenHeight() - birdHeight;
        bottomBoundary = 180;
    }

    @Override
    public void draw() {
        game.getBatch().begin();
        game.getBatch().draw(birdAnimation.getKeyFrame(stateTime, Animation.Mode.LOOPING), birdShape.getX(), birdShape.getY(), birdShape.getWidth(), birdShape.getHeight());
        game.getBatch().end();
    }

    @Override
    public void update(float delta) {
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

        stateTime += delta;
    }

    public boolean hitBoundary() {
        return birdShape.getY() <= 180 || birdShape.getY() >= topBoundary;
    }

    public Rectangle getBirdShape() {
        return birdShape;
    }
}
