package soundsystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@Configuration
@Import({CDPlayerConfig.class})
@ImportResource("classpath:cd-config.xml")
public class SoundSystemConfig {
}
