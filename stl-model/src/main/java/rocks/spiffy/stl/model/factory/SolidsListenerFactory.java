package rocks.spiffy.stl.model.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import rocks.spiffy.stl.StlAsciiBaseListener;
import rocks.spiffy.stl.StlAsciiListener;
import rocks.spiffy.stl.StlAsciiParser;
import rocks.spiffy.stl.model.Facet;
import rocks.spiffy.stl.model.Normal;
import rocks.spiffy.stl.model.Solid;
import rocks.spiffy.stl.model.Vertex;
import rocks.spiffy.stl.model.builder.*;

/**
 * ListenerFactory for a Solids object.
 *
 * This is expected to be passed to the stl parser to interpret a file. The result is Optionally provided if the solids
 * listener successfully parsed the full file. Otherwise (not finished parsing or parse failed) returns Optional.empty() *
 */
@Slf4j
public class SolidsListenerFactory extends StlAsciiBaseListener implements StlAsciiListener {

    private final VertexBuilder vertexBuilder;
    private final NormalBuilder normalBuilder;
    private final FacetBuilderFactory facetBuilderFactory;
    private final SolidsBuilder solidsBuilder;
    private FacetBuilder facetBuilder;
    private final SolidBuilderFactory solidBuilderFactory;
    private SolidBuilder solidBuilder;


    public SolidsListenerFactory(VertexBuilder vertexBuilder,
                                 NormalBuilder normalBuilder,
                                 FacetBuilderFactory facetBuilderFactory,
                                 SolidBuilderFactory solidBuilderFactory,
                                 SolidsBuilder solidsBuilder) {
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

        this.solidsBuilder = solidsBuilder;
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
        //TODO callback future to signal solids parsed
    }

    //    @Override public void enterSolids(StlAsciiParser.SolidsContext ctx);
    //    @Override public void enterSolid(StlAsciiParser.SolidContext ctx) { }
    //    @Override public void enterFacets(StlAsciiParser.FacetsContext ctx) { }
    //    @Override public void exitFacets(StlAsciiParser.FacetsContext ctx) { }
    //    @Override public void enterFacet(StlAsciiParser.FacetContext ctx) { }
    //    @Override public void enterVertex(StlAsciiParser.VertexContext ctx) { }
    //    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    //    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    //    @Override public void visitTerminal(TerminalNode node) { }
    //    @Override public void visitErrorNode(ErrorNode node) { }

}
