package ie.tcd.mengxia.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.accessibility.AccessibleStateSet;

import ie.tcd.mengxia.Assests;
import ie.tcd.mengxia.FlappyBirdGame;
import ie.tcd.mengxia.GameStatus;
import ie.tcd.mengxia.MainScreen;

public class StartButton implements Drawable {

    //initial start button texture
    private final Texture texture = Assests.START_BUTTON_TEXTURE;
    private final Sound clickSound = Assests.BUTTON_CLICK_SOUND;
    private final FlappyBirdGame game;
    private final Rectangle shape;

    public StartButton(FlappyBirdGame game) {
        this.game = game;
        shape = new Rectangle();
        shape.setWidth(texture.getWidth()/3);
        shape.setHeight(texture.getHeight()/3);
        shape.setX(game.getScreenWidth()/2 - shape.getWidth()/2);
        shape.setY(250);
    }

    // draw start button
    @Override
    public void draw() {
        game.getBatch().begin();
        game.getBatch().draw(texture, shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        game.getBatch().end();
    }

    //click start button then game status change to Game_Running status
    @Override
    public void update(float delta) {
        if (game.getStatus() != GameStatus.GAME_RUNNING && Gdx.input.isTouched()) {
            Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPoint = game.getCamera().unproject(touchPoint);
            if (shape.contains(touchPoint.x, touchPoint.y)) {
                clickSound.play();
                game.setStatus(GameStatus.GAME_RUNNING);
                game.setScreen(new MainScreen(game));
            }
        }
    }
}
