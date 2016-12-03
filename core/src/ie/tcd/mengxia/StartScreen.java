package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ie.tcd.mengxia.FlappyBirdGame;
import ie.tcd.mengxia.object.Background;
import ie.tcd.mengxia.object.Bird;
import ie.tcd.mengxia.object.StartButton;

public class StartScreen extends ScreenAdapter {
    private final FlappyBirdGame game;
    private final ie.tcd.mengxia.object.Background background;
    private final ie.tcd.mengxia.object.Bird bird;
    private final ie.tcd.mengxia.object.StartButton startButton;

    public StartScreen(FlappyBirdGame game) {
        this.game = game;
        background = new Background(game);
        bird = new Bird(game);
        startButton = new StartButton(game);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // tell the camera to update its matrices.
        OrthographicCamera camera = game.getCamera();
        camera.update();
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        SpriteBatch batch = game.getBatch();
        batch.setProjectionMatrix(camera.combined);

        background.draw();
        bird.draw();
        startButton.draw();
    }

    private void update(float delta) {
        background.update(delta);
        bird.update(delta);
        startButton.update(delta);
    }
}
