package rocks.spiffy.stl.model.factory;

import lombok.Data;
import org.antlr.v4.runtime.RecognitionException;

/**
 * A data object containing details of a failure to parse a given STL file
 */
@Data
public class ParseFaultDetails {
    private final Object offendingSymbol;
    private final int line;
    private final int charPositionInLine;
    private final String msg;
    private final RecognitionException e;

    public ParseFaultDetails(Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        this.offendingSymbol = offendingSymbol;
        this.line = line;
        this.charPositionInLine = charPositionInLine;
        this.msg = msg;
        this.e = e;
    }
}
