package rocks.spiffy.stl.parse;

import org.antlr.v4.runtime.*;
import org.junit.Test;
import rocks.spiffy.stl.StlAsciiLexer;
import rocks.spiffy.stl.StlAsciiParser;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class StlTest {

    @Test
    public void testAThing() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/camPlate.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        p.addParseListener(new PrintingStlAsciiListener());

        p.solid();

    }

}
