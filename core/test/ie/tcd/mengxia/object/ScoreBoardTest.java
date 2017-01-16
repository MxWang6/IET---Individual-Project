package ie.tcd.mengxia.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ie.tcd.mengxia.FlappyBirdGame;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by new on 16/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ScoreBoardTest {


    @Mock
    private Texture gameOverTexture;
    @Mock
    private FlappyBirdGame game;
    @Mock
    private SpriteBatch spriteBatch;

    @InjectMocks
    private ScoreBoard scoreBoard;

    @Before
    public void before() {
        when(game.getBatch()).thenReturn(spriteBatch);
        when(game.getScreenWidth()).thenReturn(120);
        when(game.getScreenHeight()).thenReturn(220);
        when(gameOverTexture.getWidth()).thenReturn(20);
        when(gameOverTexture.getHeight()).thenReturn(30);
    }

    @Test
    public void drawTest(){

        scoreBoard.draw();
        verify(spriteBatch).begin();
        verify(spriteBatch).draw(gameOverTexture, 50, 110, 20, 30);
        verify(spriteBatch).end();

    }
}
