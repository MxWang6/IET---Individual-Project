package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import ie.tcd.mengxia.Animation;
import ie.tcd.mengxia.Assests;
import ie.tcd.mengxia.FlappyBirdGame;

public class Coin implements Drawable {


    // initial features of coins intem
    private final Texture items = Assests.COIN_ITEMS_TEXTURE;
    private final Sound coinSound = Assests.COIN_TOUCH_SOUND;

    // animation coins
    private final Animation coinAnim = new Animation(0.2f,
                new TextureRegion(items, 128, 32, 32, 32),
                new TextureRegion(items, 160, 32, 32, 32),
                new TextureRegion(items, 192, 32, 32, 32),
                new TextureRegion(items, 160, 32, 32, 32));


    private final FlappyBirdGame game;
    private float stateTime = 0;
    private final Rectangle coinShape;
    private final float moveVelocity;
    private static Random random = new Random();
    private static final int RANDOM = 500;

    public Coin(FlappyBirdGame game) {
        this.game = game;

        moveVelocity = game.getScreenWidth() / 5;
        //set width and height of coins
        float coinWidth =  items.getWidth()/8;
        float coinHeight = items.getHeight()/8;

        float x = game.getScreenWidth();
        float y = game.getScreenHeight()/6;

        // make shape of coin as rectangle
        coinShape = new Rectangle();
        coinShape.setWidth(coinWidth);
        coinShape.setHeight(coinHeight);

        // produce coins random
        coinShape.setX(x);
        coinShape.setY(y+random.nextInt(RANDOM));

    }

    @Override
    public void draw() {

        game.getBatch().begin();
        game.getBatch().draw(coinAnim.getKeyFrame(stateTime, Animation.Mode.LOOPING), coinShape.getX(), coinShape.getY(), coinShape.getWidth(), coinShape.getHeight());
        game.getBatch().end();
    }

    @Override
    public void update(float delta) {

        switch (game.getStatus()) {
            case GAME_RUNNING:
                coinShape.x -= moveVelocity * delta;
                coinShape.x -= moveVelocity * delta;
                break;
            case GAME_READY:
            case GAME_OVER:
                // TODO handling other game status.
                break;
            default:
                break;
        }
        stateTime += delta;
    }

    //getCoin after bird touch the coin
    public boolean getCoins(Rectangle boundsBird) {
        final boolean isGetting = boundsBird.overlaps(coinShape);
        return isGetting;
    }

    // play sound of coin
    public void playCoinSound(){

        coinSound.play();
    }

}
