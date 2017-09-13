package rocks.spiffy.stl.model.builder;

import org.springframework.context.annotation.ComponentScan;

/**
 * factory for making solid builders
 */
@ComponentScan
public class SolidBuilderFactory {

    /**
     * @return a new instance of a solid builder
     */
    public SolidBuilder newInstance() {
        return new SolidBuilder();
    }

}
