package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background implements GameObject {
    private static final Texture texture = new Texture(Gdx.files.internal("bg.png"));
    private static final TextureRegion backgroundRegion = new TextureRegion(texture, 0, 0, 288, 384);



    private final FlappyBirdGame game;

    Background(FlappyBirdGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.getBatch().disableBlending();
        game.getBatch().begin();
        game.getBatch().draw(backgroundRegion, 0, 0, game.getScreenWidth(), game.getScreenHeight());
        game.getBatch().end();
        game.getBatch().enableBlending();
    }
}
