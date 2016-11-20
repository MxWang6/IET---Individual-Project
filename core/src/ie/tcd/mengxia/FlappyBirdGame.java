package ie.tcd.mengxia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBirdGame extends Game {
	private SpriteBatch batch;
	private int screenWidth;
	private int screenHeight;
	private float ratio;
	private OrthographicCamera camera;
	private GameStatus status;

	@Override
	public void create () {
		screenWidth = 607; //Gdx.app.getGraphics().getWidth();
		screenHeight = 1080; //Gdx.app.getGraphics().getHeight();
		ratio = screenWidth / screenHeight;   // 16/9

		batch = new SpriteBatch();
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera(screenWidth, screenHeight);
		camera.position.set(screenWidth / 2, screenHeight / 2, 0);

		// TODO we need to change to the game ready when we introduce game ready.
		status = GameStatus.GAME_START;

		setScreen(new StartScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}
}
