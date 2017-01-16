package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ie.tcd.mengxia.Assests;
import ie.tcd.mengxia.FlappyBirdGame;

public class ScoreBoard implements Drawable {

    private static final float RATIO = 0.8f;
    private Texture gameOverTexture;
    private FlappyBirdGame game;
    private float x;
    private float y;

    //private scoreboard construct for JUnit testing
    private ScoreBoard(FlappyBirdGame game, Texture gameOverTexture){
        this.game = game;
        this.gameOverTexture = gameOverTexture;
    }

    public ScoreBoard(FlappyBirdGame game) {
        this.game = game;
        gameOverTexture = Assests.GAMEOVER_TEXTURE;

    }

    @Override
    public void update(float delta) {

    }

    // draw a scoreboard texture
    @Override
    public void draw() {
        game.getBatch().begin();
        game.getBatch().draw(gameOverTexture, game.getScreenWidth()/2- gameOverTexture.getWidth()/2, game.getScreenHeight()/2, gameOverTexture.getWidth(), gameOverTexture.getHeight());
        game.getBatch().end();
    }
}
