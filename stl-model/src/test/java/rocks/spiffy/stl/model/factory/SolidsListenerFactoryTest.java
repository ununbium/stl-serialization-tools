package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.Token;
import org.junit.Test;
import rocks.spiffy.stl.StlAsciiParser;
import rocks.spiffy.stl.model.builder.VertexBuilder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SolidsListenerFactoryTest {

    @Test
    public void testExitVertex() {
        //given

        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        SolidsListenerFactory factory = new SolidsListenerFactory(vertexBuilder);

        StlAsciiParser.VertexContext ctx = new StlAsciiParser.VertexContext(null, 0);

        Token xToken = mock(Token.class);
        when(xToken.getText()).thenReturn("-1");
        ctx.vertexX = xToken;

        Token yToken = mock(Token.class);
        when(yToken.getText()).thenReturn("-2");
        ctx.vertexY = yToken;

        Token zToken = mock(Token.class);
        when(zToken.getText()).thenReturn("-3");
        ctx.vertexZ = zToken;

        //when
        factory.exitVertex(ctx);

        //then
        verify(vertexBuilder, times(1)).fromStrings("-1", "-2", "-3");

    }

}