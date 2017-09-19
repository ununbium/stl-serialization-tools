package rocks.spiffy.stl.parse;

import org.antlr.v4.runtime.*;
import org.junit.Test;
import rocks.spiffy.stl.StlAsciiLexer;
import rocks.spiffy.stl.StlAsciiParser;
import rocks.spiffy.stl.model.builder.*;
import rocks.spiffy.stl.model.factory.StlAsciiInterpreter;

import java.io.IOException;

/**
 * Easy way to demo a parse as part of early dev
 * TODO remove when not needed any more for dev validation
 */
public class StlDemoTest {

    @Test
    public void testParse() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/camPlate.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));

        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        VertexBuilder vb = new VertexBuilder();
        NormalBuilder nb = new NormalBuilder();
        FacetBuilderFactory fb = new FacetBuilderFactory();
        SolidBuilderFactory sbf = new SolidBuilderFactory();
        SolidsBuilderFactory s = new SolidsBuilderFactory();
        p.addParseListener(new StlAsciiInterpreter(vb, nb, fb, sbf, s));

        StlAsciiParser.SolidsContext solids = p.solids();

    }

}
