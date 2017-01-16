package ie.tcd.mengxia;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.junit.Assert.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by new on 15/01/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnimationTest {

    @Mock
    private TextureRegion texture1;
    @Mock
    private TextureRegion texture2;

    @Test
    public void test() {

        Animation animation = new Animation(0.1f, texture1, texture2);
        TextureRegion result = animation.getKeyFrame(0.05f, Animation.Mode.LOOPING);
        assertEquals(texture1, result);
    }

    @Test
    public void test2() {

        Animation animation = new Animation(0.1f, texture1, texture2);
        TextureRegion result = animation.getKeyFrame(0.15f, Animation.Mode.LOOPING);
        assertEquals(texture2, result);
    }

}
