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
    public void enterSolid(StlAsciiParser.SolidContext ctx) {
        log.info("entered solid");
    }

    @Override
    public void exitSolid(StlAsciiParser.SolidContext ctx) {
        log.info("solid with name " + ctx.name.getText());
    }

    @Override
    public void enterFacets(StlAsciiParser.FacetsContext ctx) {
        log.info("entered Facets");
    }

    @Override
    public void exitFacets(StlAsciiParser.FacetsContext ctx) {
        log.info("Exited Facets");
    }

    @Override
    public void enterFacet(StlAsciiParser.FacetContext ctx) {
        log.info("entered Facet");
    }

    @Override
    public void exitFacet(StlAsciiParser.FacetContext ctx) {
        log.info("Exited Facet");
    }

    @Override
    public void enterOuterLoop(StlAsciiParser.OuterLoopContext ctx) {
        log.info("entered OuterLoop");
    }

    @Override
    public void exitOuterLoop(StlAsciiParser.OuterLoopContext ctx) {
        log.info("exited OuterLoop");
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
