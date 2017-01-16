package ie.tcd.mengxia.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ie.tcd.mengxia.Assests;
import ie.tcd.mengxia.FlappyBirdGame;

//Background objects contain texture of background scene
public class Background implements Drawable {

    private TextureRegion backgroundRegion;
    private FlappyBirdGame game;

    //constructor of background
    public Background(FlappyBirdGame game) {
        this.game = game;
        backgroundRegion = new TextureRegion(Assests.BACKGROUND_TEXTURE, 0, 0, 288, 384);
    }

    //private constructor for JUnit testing
    private Background(FlappyBirdGame game, TextureRegion backgroundRegion) {
        this.game = game;
        this.backgroundRegion = backgroundRegion;
    }

    //function about drawing background scene
    @Override
    public void draw() {
        game.getBatch().disableBlending();
        game.getBatch().begin();
        game.getBatch().draw(backgroundRegion, 0, 0, game.getScreenWidth(), game.getScreenHeight());
        game.getBatch().end();
        game.getBatch().enableBlending();
    }

    @Override
    public void update(float delta) {
    }
}
