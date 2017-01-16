package ie.tcd.mengxia.object;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ie.tcd.mengxia.Animation;
import ie.tcd.mengxia.FlappyBirdGame;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by new on 15/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class BirdTest {
    @Mock
    private Texture textue;
    @Mock
    private Animation birdAnimation;
    @Mock
    private Sound upSound;
    @Mock
    private FlappyBirdGame game;

    @Mock
    private Rectangle birdShape;

    @Mock
    private SpriteBatch spriteBatch;

    @InjectMocks
    private Bird bird;

    @Before
    public void before(){

        when(game.getBatch()).thenReturn(spriteBatch);

    }

    @Test
    public void test() {
        bird.draw();

        verify(spriteBatch).begin();
       // verify(spriteBatch).draw(birdAnimation.getKeyFrame(0,Animation.Mode.LOOPING));
      //  verify(spriteBatch).end();

    }



}
