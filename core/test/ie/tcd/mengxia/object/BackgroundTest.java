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
import ie.tcd.mengxia.object.Background;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by new on 15/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class BackgroundTest {

    @Mock
    private TextureRegion backgroundRegion;
    @Mock
    private FlappyBirdGame game;
    @Mock
    private SpriteBatch spriteBatch;

    @InjectMocks
    private Background background;

    @Before
    public void before() {
        when(game.getBatch()).thenReturn(spriteBatch);
        when(game.getScreenWidth()).thenReturn(120);
        when(game.getScreenHeight()).thenReturn(330);
    }


    @Test
    public void shouldDrawTheBackground() {
        background.draw();

        verify(spriteBatch).disableBlending();
        verify(spriteBatch).begin();
        verify(spriteBatch).draw(backgroundRegion, 0, 0, 120, 330);
        verify(spriteBatch).end();
        verify(spriteBatch).enableBlending();
    }
}
