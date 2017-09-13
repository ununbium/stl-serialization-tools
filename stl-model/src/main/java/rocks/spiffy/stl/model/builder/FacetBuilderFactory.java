package rocks.spiffy.stl.model.builder;

import org.springframework.stereotype.Component;

/**
 * factory for producing facet builders
 */
@Component
public class FacetBuilderFactory {

    /**
     * @return a new instance of the factory builder
     */
    public FacetBuilder newInstance() {
        return new FacetBuilder();
    }
}
