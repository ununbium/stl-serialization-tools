package rocks.spiffy.stl.model.factory;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.util.Assert;
import rocks.spiffy.stl.StlAsciiBaseListener;
import rocks.spiffy.stl.StlAsciiListener;
import rocks.spiffy.stl.StlAsciiParser;
import rocks.spiffy.stl.model.Vertex;
import rocks.spiffy.stl.model.builder.VertexBuilder;

/**
 * ListenerFactory for a Solids object.
 *
 * This is expected to be passed to the stl parser to interpret a file. The result is Optionally provided if the solids
 * listener successfully parsed the full file. Otherwise (not finished parsing or parse failed) returns Optional.empty() *
 */
@Slf4j
public class SolidsListenerFactory extends StlAsciiBaseListener implements StlAsciiListener {

    private final VertexBuilder vertexBuilder;

    public SolidsListenerFactory(VertexBuilder vertexBuilder) {
        Assert.notNull(vertexBuilder, "Vertex builder cannot be null");
        this.vertexBuilder = vertexBuilder;
    }

    @Override
    public void exitVertex(StlAsciiParser.VertexContext ctx) {
        String x = ctx.vertexX.getText();
        String y = ctx.vertexY.getText();
        String z = ctx.vertexZ.getText();
        Vertex vertex = vertexBuilder.fromStrings(x, y, z);
        //TODO pass on to next builder
    }

    //    @Override public void enterSolid(StlAsciiParser.SolidContext ctx) { }
    //    @Override public void exitSolid(StlAsciiParser.SolidContext ctx) { }
    //    @Override public void enterFacets(StlAsciiParser.FacetsContext ctx) { }
    //    @Override public void exitFacets(StlAsciiParser.FacetsContext ctx) { }
    //    @Override public void enterFacet(StlAsciiParser.FacetContext ctx) { }
    //    @Override public void exitFacet(StlAsciiParser.FacetContext ctx) { }


    //    @Override public void enterVertex(StlAsciiParser.VertexContext ctx) { }

    //    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    //    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    //    @Override public void visitTerminal(TerminalNode node) { }
    //    @Override public void visitErrorNode(ErrorNode node) { }

}
