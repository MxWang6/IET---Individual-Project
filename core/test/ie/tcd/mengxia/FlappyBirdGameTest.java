package ie.tcd.mengxia;
import com.badlogic.gdx.Game;

import org.junit.Test;

import ie.tcd.mengxia.object.Background;
import ie.tcd.mengxia.object.Bird;
import ie.tcd.mengxia.object.Coin;

import static org.junit.Assert.*;

public class FlappyBirdGameTest {

    @Test
    public final void testFlappyBirdGame(){

        FlappyBirdGame gameJNTest = new FlappyBirdGame();
        boolean result = false;
        if (gameJNTest != null){
            result = true;
        }
        assertTrue(result);
    }
}
