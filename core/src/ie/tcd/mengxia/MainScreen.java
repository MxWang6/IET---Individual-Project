package ie.tcd.mengxia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import ie.tcd.mengxia.object.*;

public class MainScreen extends ScreenAdapter {
  //  private static final String[] pipeName = {"tube.png","tube2.png","tube3.png","tube4.png"};
    private static final Texture textureGround = new Texture(Gdx.files.internal("ground.png"));

    private final FlappyBirdGame game;
    private final Bird bird;
    private final Background background;
    private final StartButton startButton;
    private final ScoreBoard scoreBoard;
    private  Coin coin;
    private final Queue<Coin> coinlines = new ArrayBlockingQueue<ie.tcd.mengxia.object.Coin>(10);
    private final Queue<Pipeline> pipelines = new ArrayBlockingQueue<ie.tcd.mengxia.object.Pipeline>(10);
    private final Random randomPipelineNumberGenerator = new Random();
    private Pipeline firstPipeline;
    private Pipeline lastPipeline;

    private int score = 0;
    private int scoreForCoins = 0;
    private float scoreShowTime = 0;

    public MainScreen(FlappyBirdGame game) {
        this.game = game;
        bird = new Bird(game);
        background= new Background(game);
        startButton = new StartButton(game);
        firstPipeline = new Pipeline(game);
        lastPipeline = firstPipeline;
        pipelines.add(firstPipeline);
        scoreBoard = new ScoreBoard(game);
        coin = new Coin(game);
        coinlines.add(coin);
    }

    // update method for background, bird, pipeline and birdline
    private void update(float delta) {
        background.update(delta);
        for (Pipeline pipeline : pipelines) {
            pipeline.update(delta);
        }
        bird.update(delta);
        startButton.update(delta);
        for (Coin coinline : coinlines) {
            coinline.update(delta);
        }

    }

    @Override
    public void render (float delta) {
        update(delta);

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
        background.draw();

        // render the pipelines
        for (Pipeline pipeline : pipelines) {
            pipeline.draw();
        }

        batch.begin();
        batch.draw(textureGround, 0, 0, game.getScreenWidth(), game.getScreenHeight()/6);
        batch.end();

        // render the coin
        for (Coin coinline : coinlines) {
            coinline.draw();
        }

        // render the bird
        bird.draw();


        // remove off screen pipeline and assign the new first pipeline
        if (firstPipeline.getX() + firstPipeline.getWidth() <= 0) {
            pipelines.remove();
            firstPipeline = pipelines.peek();
        }

        // produce new pipleline
        if (lastPipeline.getX() + lastPipeline.getWidth() + 300 < game.getScreenWidth() ) {

            // for pipelines
            lastPipeline = new Pipeline(game);
            pipelines.add(lastPipeline);

            // for coinlines
            coin = new Coin(game);
            coinlines.add(coin);

        }

        // bump into pipe
        if(bird.hitBoundary() || collides(bird.getBirdShape()))
        {
            scoreBoard.draw();
            startButton.draw();
            // update game status with game over
            game.setStatus(GameStatus.GAME_OVER);
        }

        // pass through gap between pipes
        if (justPassed(bird.getBirdShape())) {
            score ++;
        }

        // count the score of coins
        if(getCoins(bird.getBirdShape()))
        {
            scoreForCoins ++;
        }

        // show number of pipelines on the screen
        game.getBatch().begin();
        String scoreString = "Pipe: "+Integer.toString(score);
        game.font.draw(game.getBatch(), scoreString, game.getScreenWidth()/6-50, game.getScreenHeight()/3 * 2+300);

        // show number of coinlines on the screen
        String scoreCoinsString = "Coin: " + Integer.toString(scoreForCoins);
        game.font.draw(game.getBatch(), scoreCoinsString, game.getScreenWidth()/6-50, game.getScreenHeight()/3 * 2+240);

        game.getBatch().end();
    }

    // return true when pump into pipeline
    private boolean collides(Rectangle birdShape) {
        for (ie.tcd.mengxia.object.Pipeline pipe : pipelines) {
            if (pipe.collides(birdShape)) {
                return true;
            }
        }
        return false;
    }

    // return true when pass the gap between pipeline
    private boolean justPassed(Rectangle birdShape) {
        for (ie.tcd.mengxia.object.Pipeline pipe : pipelines) {
            if (pipe.justPassed(bird.getBirdShape())) {
                return true;
            }
        }
        return false;
    }

    // return trun when bird get coins
    private boolean getCoins(Rectangle birdShape){
        for (ie.tcd.mengxia.object.Coin coin : coinlines) {
            if (coin.getCoins(birdShape)) {
                coin.playCoinSound();
                coinlines.remove();
                return true;
            }
        }
        return false;
    }
}
