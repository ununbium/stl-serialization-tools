package rocks.spiffy.stl.model.builder;

/**
 * factory for producing facet builders
 */
public class FacetBuilderFactory {

    /**
     * @return a new instance of the factory builder
     */
    public FacetBuilder newInstance() {
        return new FacetBuilder();
    }
}
