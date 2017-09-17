package rocks.spiffy.stl.model.builder;

import org.springframework.stereotype.Component;

/**
 * factory for making solid builders
 */
@Component
public class SolidsBuilderFactory {

    /**
     * @return a new instance of a solid builder
     */
    public SolidsBuilder newInstance() {
        return new SolidsBuilder();
    }

}
