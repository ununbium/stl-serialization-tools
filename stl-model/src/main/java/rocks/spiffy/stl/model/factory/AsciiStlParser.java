package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.*;
import org.springframework.stereotype.Component;
import rocks.spiffy.stl.StlAsciiParser;

/**
 * Service to parse Ascii format STL files
 */
@Component
public class AsciiStlParser {

    private final StlAsciiInterpreterFactory interpreterFactory;
    private final AntlrStlAsciiParserFactory parserFactory;

    /**
     * @param interpreterFactory
     * @param parserFactory
     */
    public AsciiStlParser(StlAsciiInterpreterFactory interpreterFactory, AntlrStlAsciiParserFactory parserFactory) {
        this.interpreterFactory = interpreterFactory;
        this.parserFactory = parserFactory;
    }

    /**
     * Parse the given input stream and return the result of the parse operation
     *
     * @param input the input to parse
     * @return the parse result
     */
    public ParseResult parse(ANTLRInputStream input) {
        final ParseResult result;

        StlAsciiParser parser = parserFactory.produce(input);

        StlFileErrorListener errorListener = new StlFileErrorListener();
        parser.addErrorListener(errorListener);

        StlAsciiInterpreter listener = interpreterFactory.produce();
        parser.addParseListener(listener);

        parser.solids();

        if(listener.hasResult()) {
            result = listener.getResult();
        } else if(errorListener.hasError()) {
            result = errorListener.getFault();
        } else {
            throw new IllegalStateException("parse stl resulted in neither a value nor an exception");
        }

        return result;
    }


}
