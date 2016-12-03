package ie.tcd.mengxia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBirdGame extends Game {
	private SpriteBatch batch;
	private static final int screenWidth = 607;
	private static final int screenHeight = 1080;
	private OrthographicCamera camera;
	private GameStatus status;
	private Music backgroundMusic;
	public static BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera(screenWidth, screenHeight);
		camera.position.set(screenWidth / 2, screenHeight / 2, 0);

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(0.1f);
		backgroundMusic.play();

		 font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);

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
