package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rocks.spiffy.stl.StlAsciiLexer;
import rocks.spiffy.stl.StlAsciiParser;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

@PrepareForTest(AntlrStlAsciiParserFactory.class)
@RunWith(PowerMockRunner.class)
public class AntlrStlAsciiParserFactoryTest {

    @Test
    public void testParse() throws Exception {
        //given
        AntlrStlAsciiParserFactory factory = new AntlrStlAsciiParserFactory();

        CharStream mockInput = mock(CharStream.class);
        StlAsciiLexer lexer = mock(StlAsciiLexer.class);
        PowerMockito.whenNew(StlAsciiLexer.class).withArguments(eq(mockInput)).thenReturn(lexer);

        CommonTokenStream commonTokenStream = mock(CommonTokenStream.class);
        PowerMockito.whenNew(CommonTokenStream.class).withArguments(lexer).thenReturn(commonTokenStream);

        StlAsciiParser mockParser = mock(StlAsciiParser.class);
        PowerMockito.whenNew(StlAsciiParser.class).withArguments(eq(commonTokenStream)).thenReturn(mockParser);

        //when
        StlAsciiParser result = factory.produce(mockInput);

        //then
        assertThat(result, is(mockParser));
    }

}