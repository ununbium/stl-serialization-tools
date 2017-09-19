package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

@PrepareForTest(StlFileErrorListener.class)
@RunWith(PowerMockRunner.class)
public class StlFileErrorListenerTest {

    @Test
    public void testErrorOccurred() throws Exception {
        //given
        StlFileErrorListener stlFileErrorListener = new StlFileErrorListener();

        Recognizer<?, ?> recognizer = mock(Recognizer.class);
        Object symbol = mock(Object.class);
        int line = 7;
        int character = 5;
        String message = "some message";
        RecognitionException exception = mock(RecognitionException.class);

        ParseFaultDetails mockDetails = mock(ParseFaultDetails.class);
        PowerMockito.whenNew(ParseFaultDetails.class)
                .withArguments(eq(symbol), eq(line), eq(character), eq(message), eq(exception))
                .thenReturn(mockDetails);

        //when
        stlFileErrorListener.syntaxError(recognizer, symbol, line, character, message, exception);

        //then
        assertTrue(stlFileErrorListener.hasError());
        assertThat(stlFileErrorListener.getFault().getFault(), is(mockDetails));
    }

    @Test
    public void testNoErrorOccured() throws Exception {
        //given

        //when
        StlFileErrorListener stlFileErrorListener = new StlFileErrorListener();

        //then
        assertFalse(stlFileErrorListener.hasError());
    }

    @Test(expected = NoSuchElementException.class)
    public void testExceptionOnBadGetFault() throws Exception {
        //given
        StlFileErrorListener stlFileErrorListener = new StlFileErrorListener();

        //when
        stlFileErrorListener.getFault();

        //then
        // exception expected
    }

}