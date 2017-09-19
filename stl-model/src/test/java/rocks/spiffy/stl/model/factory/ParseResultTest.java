package rocks.spiffy.stl.model.factory;

import org.junit.Test;
import rocks.spiffy.stl.model.Solids;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by andrew on 17/09/17.
 */
public class ParseResultTest {

    @Test
    public void testSuccess() {
        //given
        Solids m = mock(Solids.class);

        //when
        ParseResult parseResult = ParseResult.withValue(m);

        //then
        assertTrue(parseResult.isSuccess());
        assertThat(parseResult.getResult(), is(m));
    }

    @Test
    public void testFault() {
        //given
        ParseFaultDetails faultDetails = mock(ParseFaultDetails.class);

        //when
        ParseResult parseResult = ParseResult.withFault(faultDetails);

        //then
        assertFalse(parseResult.isSuccess());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSuccess_exceptionOnGetFault() {
        //given
        Solids m = mock(Solids.class);
        ParseResult parseResult = ParseResult.withValue(m);

        //when
        parseResult.getFault();

        //then
        // exception expected
    }

    @Test(expected = NoSuchElementException.class)
    public void testFault_exceptionOnGetResult() {
        //given
        ParseFaultDetails faultDetails = mock(ParseFaultDetails.class);
        ParseResult parseResult = ParseResult.withFault(faultDetails);

        //when
        parseResult.getResult();

        //then
        // exception expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSuccess_nullParameterException() {
        //given

        //when
        ParseResult.withValue(null);

        //then
        // exception expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFault_nullParameterException() {
        //given
        //when
        ParseResult.withFault(null);

        //then
        // exception expected
    }
}