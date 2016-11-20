package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GameOverText implements GameObject {

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
    public void render(float delta) {
        // update game status with game over
        game.setStatus(GameStatus.GAME_OVER);
        // draw the game over sign
        game.getBatch().begin();
        game.getBatch().draw(texture, x, y, texture.getWidth(), texture.getHeight());
        game.getBatch().end();
    }
}
