package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ie.tcd.mengxia.FlappyBirdGame;
import ie.tcd.mengxia.GameStatus;

public class GameOverText implements Drawable {

    private static final Texture texture = new Texture(Gdx.files.internal("gameOver.png")); // 136 * 96
    private static final int gameOverWidth = 352; // in pixel
    private static final int gameOverheight = 68; // in pixel

    private final FlappyBirdGame game;
    private int x;
    private int y;

    public GameOverText(FlappyBirdGame game) {
        this.game = game;

        // initial position (x, y)
        x = game.getScreenWidth()/2 - gameOverWidth/2;
        y = game.getScreenHeight()/2 - gameOverheight/2;
    }

    @Override
    public void draw() {
        // draw the game over sign
        game.getBatch().begin();
        game.getBatch().draw(texture, x, y, texture.getWidth(), texture.getHeight());
        game.getBatch().end();
    }

    @Override
    public void update(float delta) {

    }
}
