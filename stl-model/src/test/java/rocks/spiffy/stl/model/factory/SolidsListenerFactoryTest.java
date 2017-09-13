package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.Token;
import org.junit.Test;
import rocks.spiffy.stl.StlAsciiParser;
import rocks.spiffy.stl.model.Facet;
import rocks.spiffy.stl.model.Normal;
import rocks.spiffy.stl.model.Vertex;
import rocks.spiffy.stl.model.builder.FacetBuilder;
import rocks.spiffy.stl.model.builder.FacetBuilderFactory;
import rocks.spiffy.stl.model.builder.NormalBuilder;
import rocks.spiffy.stl.model.builder.VertexBuilder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SolidsListenerFactoryTest {

    @Test
    public void testFacetBuilderFactoryCalledOnConstruct() {
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);


        FacetBuilder facetBuilder = mock(FacetBuilder.class);
        when(facetBuilderFactory.newInstance()).thenReturn(facetBuilder);


        //when
        new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory);


        //then
        verify(facetBuilderFactory).newInstance();
    }

    @Test
    public void testExitVertex() {
        //given
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);

        FacetBuilder facetBuilder = mock(FacetBuilder.class);
        when(facetBuilderFactory.newInstance()).thenReturn(facetBuilder);

        SolidsListenerFactory factory = new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory);

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

        Vertex vertex = mock(Vertex.class);
        when(vertexBuilder.fromStrings("-1", "-2", "-3")).thenReturn(vertex);

        //when
        factory.exitVertex(ctx);

        //then
        verify(facetBuilder).addVertex(vertex);
    }

    @Test
    public void testExitFacet() {
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);


        FacetBuilder facetBuilder = mock(FacetBuilder.class);
        when(facetBuilderFactory.newInstance()).thenReturn(facetBuilder);

        SolidsListenerFactory factory = new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory);

        StlAsciiParser.FacetContext ctx = new StlAsciiParser.FacetContext(null, 0);

        Token xToken = mock(Token.class);
        when(xToken.getText()).thenReturn("-1");
        ctx.normalX = xToken;

        Token yToken = mock(Token.class);
        when(yToken.getText()).thenReturn("-2");
        ctx.normalY = yToken;

        Token zToken = mock(Token.class);
        when(zToken.getText()).thenReturn("-3");
        ctx.normalZ = zToken;

        Normal normal = mock(Normal.class);
        when(normalBuilder.fromStrings("-1", "-2", "-3")).thenReturn(normal);

        Facet facet = mock(Facet.class);
        when(facetBuilder.generateFacet(normal)).thenReturn(facet);

        //when
        factory.exitFacet(ctx);

        //then
        verify(facetBuilderFactory, times(2)).newInstance();
    }

}