package ie.tcd.mengxia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ie.tcd.mengxia.object.Background;
import ie.tcd.mengxia.object.Bird;
import ie.tcd.mengxia.object.StartButton;

/**
 * Created by new on 15/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class StartScreenTest {

    @Mock
    private FlappyBirdGame game;
    @Mock
    private Background background;
    @Mock
    private Bird bird;
    @Mock
    private StartButton startButton;

    @InjectMocks
    private StartScreen startScreen;

    @Test
    public void testRenderStartScreen() {
        startScreen.render(0.5f);
    }
}
