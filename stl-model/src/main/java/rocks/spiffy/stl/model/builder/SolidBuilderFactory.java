package rocks.spiffy.stl.model.builder;


import org.springframework.stereotype.Component;

/**
 * factory for making solid builders
 */
@Component
public class SolidBuilderFactory {

    /**
     * @return a new instance of a solid builder
     */
    public SolidBuilder newInstance() {
        return new SolidBuilder();
    }

}
