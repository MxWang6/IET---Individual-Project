package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ie.tcd.mengxia.FlappyBirdGame;

public class ScoreBoard implements Drawable {
    private static final float RATIO = 0.8f;
    private static final Texture scoreBoardTexture = new Texture(Gdx.files.internal("scoreboard.png"));
    private static final Texture gameOverTexture = new Texture(Gdx.files.internal("gameOver.png"));
    private final FlappyBirdGame game;
    private float x;
    private float y;

    public ScoreBoard(FlappyBirdGame game) {
        this.game = game;

        x = game.getScreenWidth()/2 - scoreBoardTexture.getWidth()/2 * RATIO;
        y = game.getScreenHeight()/2 - scoreBoardTexture.getHeight()/2 * RATIO;
    }
    @Override
    public void update(float delta) {

    }

    @Override
    public void draw() {
        game.getBatch().begin();
        game.getBatch().draw(scoreBoardTexture, x, y, scoreBoardTexture.getWidth() * RATIO, scoreBoardTexture.getHeight() * RATIO);
        game.getBatch().draw(gameOverTexture, game.getScreenWidth()/2 - gameOverTexture.getWidth()/2, game.getScreenHeight()/2 - gameOverTexture.getHeight()/2 + 200, gameOverTexture.getWidth(), gameOverTexture.getHeight());
        game.getBatch().end();
    }
}
