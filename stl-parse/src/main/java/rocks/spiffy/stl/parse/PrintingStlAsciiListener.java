package rocks.spiffy.stl.parse;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import rocks.spiffy.stl.StlAsciiListener;
import rocks.spiffy.stl.StlAsciiParser;

/**
 * Prints out
 */
@Slf4j
public class PrintingStlAsciiListener implements StlAsciiListener {

    @Override
    public void enterSolids(StlAsciiParser.SolidsContext ctx) {
        log.info("entered solids");
    }

    @Override
    public void exitSolids(StlAsciiParser.SolidsContext ctx) {
        log.info("exited solids");
    }

    @Override
    public void enterSolid(StlAsciiParser.SolidContext ctx) {
        log.info("entered solid");
    }

    @Override
    public void exitSolid(StlAsciiParser.SolidContext ctx) {
        log.info("solid with name " + ctx.name.getText());
    }

    @Override
    public void enterFacets(StlAsciiParser.FacetsContext ctx) {
        log.info("Entered Facets");
    }

    @Override
    public void exitFacets(StlAsciiParser.FacetsContext ctx) {
        log.info("Exited Facets");
    }

    @Override
    public void enterFacet(StlAsciiParser.FacetContext ctx) {}

    @Override
    public void exitFacet(StlAsciiParser.FacetContext ctx) {
        String nX = ctx.normalX.getText();
        String nY = ctx.normalY.getText();
        String nZ = ctx.normalZ.getText();

        log.info(String.format("Facet n:[%s %s %s]", nX, nY, nZ));
    }

    @Override
    public void enterVertex(StlAsciiParser.VertexContext ctx) {
    }

    @Override
    public void exitVertex(StlAsciiParser.VertexContext ctx) {
        String vx = ctx.vertexX.getText();
        String vy = ctx.vertexY.getText();
        String vz = ctx.vertexZ.getText();
        log.info(String.format("vertex [%s %s %s]", vx, vy, vz));
    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
