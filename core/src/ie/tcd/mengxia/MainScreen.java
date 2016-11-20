package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class MainScreen extends ScreenAdapter {
    private static final String[] pipeName = {"tube.png","tube2.png","tube3.png","tube4.png"};

    private final FlappyBirdGame game;
    private final Bird bird;
    private final Background background;
    private final GameOverText gameOver;
    private final StartButton startButton;
    private final Queue<Pipeline> pipelines = new ArrayBlockingQueue<Pipeline>(10);
    private final Random randomPipelineNumberGenerator = new Random();

    private Pipeline firstPipeline;
    private Pipeline lastPipeline;

    public MainScreen(FlappyBirdGame game) {
        this.game = game;
        bird = new Bird(game);
        background= new Background(game);
        gameOver = new GameOverText(game);
        startButton = new StartButton(game);
        firstPipeline = new Pipeline(game, new Texture(Gdx.files.internal(pipeName[1])));
        lastPipeline = firstPipeline;
        pipelines.add(firstPipeline);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        OrthographicCamera camera = game.getCamera();
        camera.update();
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        SpriteBatch batch = game.getBatch();
        batch.setProjectionMatrix(camera.combined);

        // render the background scene
        background.render(delta);

        // render the pipelines
        for (Pipeline pipeline : pipelines) {
            pipeline.render(delta);
        }

        // render the bird
        bird.render(delta);

        // remove off screen pipeline and assign the new first pipeline
        if (firstPipeline.getX() + firstPipeline.getWidth() <= 0) {
            pipelines.remove();
            firstPipeline = pipelines.peek();
        }

        // produce new pipleline
        if (lastPipeline.getX() + lastPipeline.getWidth() + 150 < game.getScreenWidth() ) {
            int pipeNumber = randomPipelineNumberGenerator.nextInt(4);
            lastPipeline = new Pipeline(game, new Texture(Gdx.files.internal(pipeName[pipeNumber])));
            pipelines.add(lastPipeline);
        }

        if(bird.hitBoundary())
        {
            gameOver.render(delta);
            startButton.render(delta);
        }
    }
}
