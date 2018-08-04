package soundsystem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {
    @Autowired
    private MediaPlayer player;

    @Autowired
    private CompactDisk cd;

    @Test
    public void test() {
        assertNotNull(cd);
    }

    @Test
    public void play(){
        player.play();
    }
}