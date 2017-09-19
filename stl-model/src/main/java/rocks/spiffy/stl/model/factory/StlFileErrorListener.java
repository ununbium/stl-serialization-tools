package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.NoSuchElementException;

/**
 * Listener for faults parsing an STL file
 */
public class StlFileErrorListener extends BaseErrorListener {

    private ParseFaultDetails details;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        details = new ParseFaultDetails(offendingSymbol, line, charPositionInLine, msg, e);
    }

    /**
     * @return true if an error was received
     */
    public boolean hasError() {
        return details != null;
    }

    /**
     * Get the parse result instance. It is required to check haError() is true before calling this method.
     *
     * @throws NoSuchElementException if the result is not present when called
     * @return the product of the interpreter, a parse results instance
     */
    public ParseResult getFault() {

        if(details==null) {
            throw new NoSuchElementException("cannot access non-present error details");
        }

        return ParseResult.withFault(details);
    }

}
