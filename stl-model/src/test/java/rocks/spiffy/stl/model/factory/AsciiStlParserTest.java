package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rocks.spiffy.stl.StlAsciiParser;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@PrepareForTest({AsciiStlParser.class, StlAsciiParser.class})
@RunWith(PowerMockRunner.class)
public class AsciiStlParserTest {

    @Test
    public void testParseSuccess() {
        //given
        StlAsciiInterpreterFactory interpreterFactory = mock(StlAsciiInterpreterFactory.class);
        AntlrStlAsciiParserFactory parserFactory = mock(AntlrStlAsciiParserFactory.class);

        AsciiStlParser asciiStlParser = new AsciiStlParser(interpreterFactory, parserFactory);

        ANTLRInputStream in = mock(ANTLRInputStream.class);

        StlAsciiInterpreter interpreter = mock(StlAsciiInterpreter.class);
        when(interpreterFactory.produce()).thenReturn(interpreter);

        StlAsciiParser parser = PowerMockito.mock(StlAsciiParser.class);
        when(parserFactory.produce(in)).thenReturn(parser);

        ParseResult mockParseResult = mock(ParseResult.class);
        when(interpreter.hasResult()).thenReturn(true);
        when(interpreter.getResult()).thenReturn(mockParseResult);

        PowerMockito.doReturn(null).when(parser).solids();

        //when
        ParseResult result = asciiStlParser.parse(in);

        //then
        verify(parser).addErrorListener(any());
        verify(parser).addParseListener(interpreter);

        assertThat(result, is(mockParseResult));
    }

    @Test
    public void testParseFailure() throws Exception {
        //given
        StlAsciiInterpreterFactory interpreterFactory = mock(StlAsciiInterpreterFactory.class);
        AntlrStlAsciiParserFactory parserFactory = mock(AntlrStlAsciiParserFactory.class);

        AsciiStlParser asciiStlParser = new AsciiStlParser(interpreterFactory, parserFactory);

        ANTLRInputStream in = mock(ANTLRInputStream.class);

        StlAsciiInterpreter interpreter = mock(StlAsciiInterpreter.class);
        when(interpreterFactory.produce()).thenReturn(interpreter);

        StlAsciiParser parser = PowerMockito.mock(StlAsciiParser.class);
        when(parserFactory.produce(in)).thenReturn(parser);

        when(interpreter.hasResult()).thenReturn(false);

        PowerMockito.doReturn(null).when(parser).solids();

        StlFileErrorListener listener = mock(StlFileErrorListener.class);
        PowerMockito.whenNew(StlFileErrorListener.class).withAnyArguments().thenReturn(listener);
        when(listener.hasError()).thenReturn(true);
        ParseResult mockParseResult = mock(ParseResult.class);
        when(listener.getFault()).thenReturn(mockParseResult);

        //when
        ParseResult result = asciiStlParser.parse(in);

        //then
        verify(parser).addErrorListener(any());
        verify(parser).addParseListener(interpreter);

        assertThat(result, is(mockParseResult));
    }

    @Test(expected = IllegalStateException.class)
    public void testParseFault_badState() {
        //given
        StlAsciiInterpreterFactory interpreterFactory = mock(StlAsciiInterpreterFactory.class);
        AntlrStlAsciiParserFactory parserFactory = mock(AntlrStlAsciiParserFactory.class);

        AsciiStlParser asciiStlParser = new AsciiStlParser(interpreterFactory, parserFactory);

        ANTLRInputStream in = mock(ANTLRInputStream.class);

        StlAsciiInterpreter interpreter = mock(StlAsciiInterpreter.class);
        when(interpreterFactory.produce()).thenReturn(interpreter);

        StlAsciiParser parser = PowerMockito.mock(StlAsciiParser.class);
        when(parserFactory.produce(in)).thenReturn(parser);

        PowerMockito.doReturn(null).when(parser).solids();

        //when
        asciiStlParser.parse(in);

        //then
        // exception expected
    }

}