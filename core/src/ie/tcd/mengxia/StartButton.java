package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class StartButton implements GameObject {
    private static final Texture texture = new Texture(Gdx.files.internal("start.png"));
    private static final Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("audio/click.wav"));
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

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPoint = game.getCamera().unproject(touchPoint);
            if (shape.contains(touchPoint.x, touchPoint.y)) {
                clickSound.play();
                game.setStatus(GameStatus.GAME_RUNNING);
                game.setScreen(new MainScreen(game));
            }
        }


        game.getBatch().begin();
        game.getBatch().draw(texture, shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        game.getBatch().end();
    }
}