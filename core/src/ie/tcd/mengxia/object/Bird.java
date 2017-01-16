package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import ie.tcd.mengxia.Animation;
import ie.tcd.mengxia.Assests;
import ie.tcd.mengxia.FlappyBirdGame;

public class Bird implements Drawable {
    private final Texture texture;
    private final Animation birdAnimation;
    private final Sound upSound;
    private final FlappyBirdGame game;
    private final float dropVelocity;
    private final float upDistance;
    private final float topBoundary;
    private final float bottomBoundary;
    private final Rectangle birdShape;
    private float stateTime = 0;

    // private construct for bird class JUnit testing
    private Bird(float stateTime, Texture texture, Animation birdAnimation, Sound upSound, FlappyBirdGame game, float dropVelocity, float upDistance, float topBoundary, float bottomBoundary, Rectangle birdShape) {
        this.stateTime = stateTime;
        this.texture = texture;
        this.birdAnimation = birdAnimation;
        this.upSound = upSound;
        this.game = game;
        this.dropVelocity = dropVelocity;
        this.upDistance = upDistance;
        this.topBoundary = topBoundary;
        this.bottomBoundary = bottomBoundary;
        this.birdShape = birdShape;
    }

    public Bird(FlappyBirdGame game) {
        this.game = game;
        dropVelocity = game.getScreenHeight() / 5;  // the speed of dropping
        upDistance = 15;
        upSound = Assests.BIRD_UP_SOUND;
        texture = Assests.BIRD_TEXTURE;

        // animation bird texture
        birdAnimation = new Animation(0.3f,
                new TextureRegion(texture, 0, 0, texture.getWidth() / 3, texture.getHeight()),
                new TextureRegion(texture, texture.getWidth() / 3, 0, texture.getWidth() / 3, texture.getHeight()),
                new TextureRegion(texture, texture.getWidth() / 3 * 2, 0, texture.getWidth() / 3, texture.getHeight()));

        float birdWidth = texture.getWidth() / 3 * 2;
        float birdHeight = texture.getHeight() * 2;

        // initial bird position (x, y) with width and height in the view port coord as a similar rectangle
        birdShape = new Rectangle();
        birdShape.setWidth(birdWidth);
        birdShape.setHeight(birdHeight);
        birdShape.setX(game.getScreenWidth() / 2 - birdWidth / 2);
        birdShape.setY(game.getScreenHeight() / 2 - birdHeight / 2);

        // top and bottom boundary that brid can fly
        topBoundary = game.getScreenHeight() - birdHeight;
        bottomBoundary = 180;
    }


    // draw animation texture of bird
    @Override
    public void draw() {
        game.getBatch().begin();
        game.getBatch().draw(birdAnimation.getKeyFrame(stateTime, Animation.Mode.LOOPING), birdShape.getX(), birdShape.getY(), birdShape.getWidth(), birdShape.getHeight());
        game.getBatch().end();
    }

    // update method for different game status
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

    //hitBoundary when bird fly too high or touch the ground
    public boolean hitBoundary() {
        return birdShape.getY() <= 180 || birdShape.getY() >= topBoundary;
    }

    public Rectangle getBirdShape() {
        return birdShape;
    }
}
