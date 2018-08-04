package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@Configuration

public class CDConfig {

    @Bean
    public CompactDisk compactDisk() {
        return new StgPeppers();
    }

}
