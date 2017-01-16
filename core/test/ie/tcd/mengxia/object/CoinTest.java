package ie.tcd.mengxia.object;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ie.tcd.mengxia.FlappyBirdGame;

import static org.junit.Assert.assertTrue;

/**
 * Created by new on 16/01/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CoinTest {

    @Mock
    private Texture items;

    @Mock
    private Sound coinSound;

    @Mock
    private FlappyBirdGame game;

    @Mock
    private Rectangle birdRectangle;

    @InjectMocks
    private Coin coin;



    @Test
    public void getCoinsTest(){
        //coin.draw();

      boolean result = coin.getCoins(birdRectangle);
       assertTrue(result);
    }
}
