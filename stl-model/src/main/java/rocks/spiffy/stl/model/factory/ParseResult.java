package rocks.spiffy.stl.model.factory;

import org.springframework.util.Assert;
import rocks.spiffy.stl.model.Solid;
import rocks.spiffy.stl.model.Solids;

import java.util.NoSuchElementException;

/**
 * The result of a parse (either a failure to parse or )
 */
public class ParseResult {

    private final Solids solids;
    private final ParseFaultDetails parseFaultDetails;

    private ParseResult(Solids solids, ParseFaultDetails parseFaultDetails) {
        this.solids = solids;
        this.parseFaultDetails = parseFaultDetails;
    }

    /**
     * @return true if this result represents a successful parse
     */
    public boolean isSuccess() {
        return solids != null && parseFaultDetails == null;
    }

    /**
     * get the successfully parsed result. It is required to check that isSuccess() is true before calling this method.
     *
     * @throws NoSuchElementException if there was no result (see isSuccess())
     * @return the successfully parsed result
     */
    public Solids getResult() {
        if(!isSuccess()) {
            throw new NoSuchElementException("cannot access non-present result");
        }
        return solids;
    }

    /**
     * get the parse failure details. It is required to check that isSuccess() is false before calling this method.
     *
     * @throws NoSuchElementException if there was no fault (see isSuccess())
     * @return the successfully parsed result
     */
    public ParseFaultDetails getFault() {
        if(isSuccess()) {
            throw new NoSuchElementException("cannot access non-present fault");
        }
        return parseFaultDetails;
    }

    /**
     * Create a success ParseResult with the given (non-null) Solids
     *
     * @param solids the result of the parse
     * @return a success ParseResult with the given (non-null) Solids
     */
    public static ParseResult withValue(Solids solids) {
        Assert.notNull(solids, "cannot create a parse results with a null solids object");
        return new ParseResult(solids, null);
    }

    /**
     * Create a failed ParseResult with the given (non-null) cause
     *
     * @param parseFaultDetails the cause of the fault
     * @return a failed ParseResult with the given (non-null) cause
     */
    public static ParseResult withFault(ParseFaultDetails parseFaultDetails) {
        Assert.notNull(parseFaultDetails, "cannot create a parse results with a null ParseFaultDetails object");
        return new ParseResult(null, parseFaultDetails);
    }
}
