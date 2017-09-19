package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.springframework.stereotype.Component;
import rocks.spiffy.stl.model.builder.*;

/**
 * Factory for creating a new fully dependency wired StlAsciiInterpreter
 */
@Component
public class StlAsciiInterpreterFactory {

    private final VertexBuilder vertexBuilder;
    private final NormalBuilder normalBuilder;
    private final FacetBuilderFactory facetBuilderFactory;
    private final SolidBuilderFactory solidBuilderFactory;
    private final SolidsBuilderFactory solidsBuilderFactory;

    /**
     * @param vertexBuilder
     * @param normalBuilder
     * @param facetBuilderFactory
     * @param solidBuilderFactory
     * @param solidsBuilderFactory
     */
    public StlAsciiInterpreterFactory(VertexBuilder vertexBuilder,
                          NormalBuilder normalBuilder,
                          FacetBuilderFactory facetBuilderFactory,
                          SolidBuilderFactory solidBuilderFactory,
                          SolidsBuilderFactory solidsBuilderFactory) {
        this.vertexBuilder = vertexBuilder;
        this.normalBuilder = normalBuilder;
        this.facetBuilderFactory = facetBuilderFactory;
        this.solidBuilderFactory = solidBuilderFactory;
        this.solidsBuilderFactory = solidsBuilderFactory;
    }

    public StlAsciiInterpreter produce() {
        return new StlAsciiInterpreter(vertexBuilder, normalBuilder, facetBuilderFactory,
                solidBuilderFactory, solidsBuilderFactory);
    }
}
