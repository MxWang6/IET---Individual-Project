package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ie.tcd.mengxia.Animation;

public class Coin {
    private static final Texture items = new Texture(Gdx.files.internal("items.png"));
    private static final Animation coinAnim = new Animation(0.2f,
            new TextureRegion(items, 128, 32, 32, 32),
            new TextureRegion(items, 160, 32, 32, 32),
            new TextureRegion(items, 192, 32, 32, 32),
            new TextureRegion(items, 160, 32, 32, 32));

    public static final float COIN_WIDTH = 0.5f;
    public static final float COIN_HEIGHT = 0.8f;
    public static final int COIN_SCORE = 10;

}
