package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Component;
import rocks.spiffy.stl.StlAsciiLexer;
import rocks.spiffy.stl.StlAsciiParser;

/**
 * A factory for producing
 */
@Component
public class AntlrStlAsciiParserFactory {

    /**
     * Produce a new stl ascii parser for the given input
     *
     * @param input the input to parse
     * @return a parser for the given input
     */
    public StlAsciiParser produce(CharStream input) {
        StlAsciiLexer asciiLexer = new StlAsciiLexer(input);
        StlAsciiParser stlAsciiParser = new StlAsciiParser(new CommonTokenStream(asciiLexer));

        return stlAsciiParser;
    }
}
