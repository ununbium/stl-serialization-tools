package rocks.spiffy.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A starter configuration to scan relevant beans
 */
@ComponentScan("rocks.spiffy.stl")
@Configuration
public class StarterConfiguration {
}
