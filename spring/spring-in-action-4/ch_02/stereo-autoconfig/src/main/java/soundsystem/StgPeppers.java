package soundsystem;

import org.springframework.stereotype.Component;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@Component
public class StgPeppers implements CompactDisk {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
