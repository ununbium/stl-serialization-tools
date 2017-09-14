package rocks.spiffy.stl.parse;

import org.antlr.v4.runtime.*;
import org.junit.Test;
import rocks.spiffy.stl.StlAsciiLexer;
import rocks.spiffy.stl.StlAsciiParser;

import java.io.IOException;

/**
 * Integration tests mostly asserting that the grammar is doing the right thing in various cases (valid/invalid).
 */
public class StlParseIntegrationTest {

    @Test
    public void testParse_Valid() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/stl/samples/small_Valid.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        p.addParseListener(new PrintingStlAsciiListener());

        p.solids();
    }

    @Test
    public void testParse_multipleSolid_Valid() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/stl/samples/small_multipleSolid_Valid.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        p.addParseListener(new PrintingStlAsciiListener());

        p.solids();
    }

    @Test
    public void testParse_Valid_WeirdSpaces() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/stl/samples/smallWeirdSpaces_Valid.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        p.solids();
    }

    @Test(expected = IllegalStateException.class)
    public void testParse_invalidExtraVertex() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/stl/samples/small_TooManyVertexes.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        p.solids();
    }

    @Test(expected = IllegalStateException.class)
    public void testParse_invalidMissingVertex() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/stl/samples/small_Invalid_MissingVertex.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        p.solids();
    }

    @Test(expected = IllegalStateException.class)
    public void testParse_invalidEmptyName() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/stl/samples/small_Invalid_EmptyName.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        p.solids();
    }

    @Test(expected = IllegalStateException.class)
    public void testParse_missingNormal() throws IOException {
        StlAsciiLexer l = new StlAsciiLexer(new ANTLRInputStream(getClass().getResourceAsStream("/stl/samples/small_Invalid_MissingNormal.stl")));
        StlAsciiParser p = new StlAsciiParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        p.solids();
    }

}
