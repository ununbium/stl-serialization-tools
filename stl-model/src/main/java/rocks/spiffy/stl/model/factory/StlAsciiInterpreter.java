package rocks.spiffy.stl.model.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import rocks.spiffy.stl.StlAsciiBaseListener;
import rocks.spiffy.stl.StlAsciiListener;
import rocks.spiffy.stl.StlAsciiParser;
import rocks.spiffy.stl.model.*;
import rocks.spiffy.stl.model.builder.*;

import java.util.NoSuchElementException;

/**
 * ListenerFactory for a Solids object.
 *
 * This is expected to be passed to the stl parser to interpret a file. The result is Optionally provided if the solids
 * listener successfully parsed the full file. Otherwise (not finished parsing or parse failed) returns Optional.empty() *
 */
@Slf4j
public class StlAsciiInterpreter extends StlAsciiBaseListener {

    private final VertexBuilder vertexBuilder;
    private final NormalBuilder normalBuilder;
    private final FacetBuilderFactory facetBuilderFactory;
    private final SolidsBuilder solidsBuilder;
    private FacetBuilder facetBuilder;
    private final SolidBuilderFactory solidBuilderFactory;
    private SolidBuilder solidBuilder;
    private Solids solids;


    public StlAsciiInterpreter(VertexBuilder vertexBuilder,
                               NormalBuilder normalBuilder,
                               FacetBuilderFactory facetBuilderFactory,
                               SolidBuilderFactory solidBuilderFactory,
                               SolidsBuilderFactory solidsBuilderFactory) {
        Assert.notNull(vertexBuilder, "Vertex builder cannot be null");
        Assert.notNull(normalBuilder, "Normal builder cannot be null");
        Assert.notNull(facetBuilderFactory, "Facet builder factory cannot be null");
        Assert.notNull(solidBuilderFactory, "Solid builder factory cannot be null");
        this.vertexBuilder = vertexBuilder;
        this.normalBuilder = normalBuilder;

        this.facetBuilderFactory = facetBuilderFactory;
        facetBuilder = facetBuilderFactory.newInstance();

        this.solidBuilderFactory = solidBuilderFactory;
        this.solidBuilder = solidBuilderFactory.newInstance();

        this.solidsBuilder = solidsBuilderFactory.newInstance();
    }

    @Override
    public void exitVertex(StlAsciiParser.VertexContext ctx) {
        String x = ctx.vertexX.getText();
        String y = ctx.vertexY.getText();
        String z = ctx.vertexZ.getText();
        Vertex vertex = vertexBuilder.fromStrings(x, y, z);
        facetBuilder.addVertex(vertex);
    }

    @Override
    public void exitFacet(StlAsciiParser.FacetContext ctx) {
        String x = ctx.normalZ.getText();
        String y = ctx.normalY.getText();
        String z = ctx.normalX.getText();

        Normal normal = normalBuilder.fromStrings(z, y, x);
        Facet facet = facetBuilder.generateFacet(normal);

        solidBuilder.addFacet(facet);

        facetBuilder = facetBuilderFactory.newInstance();
    }

    @Override
    public void exitSolid(StlAsciiParser.SolidContext ctx) {
        String name = ctx.name.getText();
        Solid solid = solidBuilder.generateSolid(name);
        solidsBuilder.addSolid(solid);
        solidBuilder = solidBuilderFactory.newInstance();
    }

    @Override
    public void exitSolids(StlAsciiParser.SolidsContext ctx) {
        solids = solidsBuilder.generateSolids();
        log.info(solids.toString());
    }

    /**
     * @return true if the solids object has been produced by the interpreter
     */
    public boolean hasResult() {
        return solids != null;
    }

    /**
     * Get the parse result instance. It is required to check hasResult() is true before calling this method.
     *
     * @throws NoSuchElementException if the result is not present when called
     * @return the product of the interpreter, a parse results instance
     */
    public ParseResult getResult() {

        if(solids==null) {
            throw new NoSuchElementException("cannot access non-present solids");
        }

        return ParseResult.withValue(solids);
    }

}
