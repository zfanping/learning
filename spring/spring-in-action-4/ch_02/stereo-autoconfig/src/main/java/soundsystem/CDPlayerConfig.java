package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@Configuration
@Import(CDConfig.class)
public class CDPlayerConfig {

    @Bean
    public MediaPlayer player(CompactDisk compactDisk) {
        return new CDPlayer(compactDisk);
    }
}
