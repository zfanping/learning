package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@Component
public class CDPlayer implements MediaPlayer {
    @Autowired
    private CompactDisk cd;

    public CDPlayer(CompactDisk compactDisk) {

    }

    @Override
    public void play() {
        cd.play();
    }
}
