package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ie.tcd.mengxia.FlappyBirdGame;

public class Background implements Drawable {
    private static final Texture texture = new Texture(Gdx.files.internal("bg.png"));
    private static final TextureRegion backgroundRegion = new TextureRegion(texture, 0, 0, 288, 384);



    private final FlappyBirdGame game;

    public Background(FlappyBirdGame game) {
        this.game = game;
    }

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
