package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by new on 15/01/2017.
 */

// Assests class is used to preserve all texture and sound in the game
public final class Assests {
    public static final Texture BACKGROUND_TEXTURE = new Texture(Gdx.files.internal("bg.png"));
    public static final Texture BIRD_TEXTURE = new Texture(Gdx.files.internal("birdanimation.png")); // 136 * 96
    public static final Sound BIRD_UP_SOUND = Gdx.audio.newSound(Gdx.files.internal("audio/up.wav"));
    public static final Sound BUTTON_CLICK_SOUND = Gdx.audio.newSound(Gdx.files.internal("audio/click.wav"));
    public static final Texture START_BUTTON_TEXTURE = new Texture(Gdx.files.internal("start.png"));
    public static final Texture COIN_ITEMS_TEXTURE = new Texture(Gdx.files.internal("items.png"));
    public static final Sound COIN_TOUCH_SOUND = Gdx.audio.newSound(Gdx.files.internal("audio/coin.mp3"));
    public static final Texture GAMEOVER_TEXTURE = new Texture(Gdx.files.internal("gameOver.png"));


}
