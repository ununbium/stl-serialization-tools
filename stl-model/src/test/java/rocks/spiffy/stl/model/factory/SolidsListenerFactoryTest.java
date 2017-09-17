package rocks.spiffy.stl.model.factory;

import org.antlr.v4.runtime.Token;
import org.junit.Test;
import rocks.spiffy.stl.StlAsciiParser;
import rocks.spiffy.stl.model.*;
import rocks.spiffy.stl.model.builder.*;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SolidsListenerFactoryTest {

    @Test
    public void testFacetBuilderFactoryCalledOnConstruct() {
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory s = mock(SolidsBuilderFactory.class);

        FacetBuilder facetBuilder = mock(FacetBuilder.class);
        when(facetBuilderFactory.newInstance()).thenReturn(facetBuilder);


        //when
        new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory, solidBuilderFactory, s);

        //then
        verify(facetBuilderFactory).newInstance();
    }

    @Test
    public void testSolidBuilderFactoryCalledOnConstruct() {
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory s = mock(SolidsBuilderFactory.class);

        SolidBuilder solidBuilder = mock(SolidBuilder.class);
        when(solidBuilderFactory.newInstance()).thenReturn(solidBuilder);

        //when
        new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory, solidBuilderFactory, s);


        //then
        verify(solidBuilderFactory).newInstance();
    }

    @Test
    public void testExitVertex() {
        //given
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory s = mock(SolidsBuilderFactory.class);

        SolidBuilder solidBuilder = mock(SolidBuilder.class);
        when(solidBuilderFactory.newInstance()).thenReturn(solidBuilder);

        FacetBuilder facetBuilder = mock(FacetBuilder.class);
        when(facetBuilderFactory.newInstance()).thenReturn(facetBuilder);

        SolidsListenerFactory factory =
                new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory, solidBuilderFactory, s);

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
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory s = mock(SolidsBuilderFactory.class);

        SolidBuilder solidBuilder = mock(SolidBuilder.class);
        when(solidBuilderFactory.newInstance()).thenReturn(solidBuilder);

        FacetBuilder facetBuilder = mock(FacetBuilder.class);
        when(facetBuilderFactory.newInstance()).thenReturn(facetBuilder);

        SolidsListenerFactory factory =
                new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory, solidBuilderFactory,s);

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
        verify(solidBuilder).addFacet(facet);
    }

    @Test
    public void testExitSolid() {
        //given
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory solidsBuilderFactory = mock(SolidsBuilderFactory.class);

        SolidsBuilder solidsFactory = mock(SolidsBuilder.class);
        when(solidsBuilderFactory.newInstance()).thenReturn(solidsFactory);

        SolidBuilder solidBuilder = mock(SolidBuilder.class);
        when(solidBuilderFactory.newInstance()).thenReturn(solidBuilder);

        FacetBuilder facetBuilder = mock(FacetBuilder.class);
        when(facetBuilderFactory.newInstance()).thenReturn(facetBuilder);

        SolidsListenerFactory factory = new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory,
                solidBuilderFactory, solidsBuilderFactory);

        StlAsciiParser.SolidContext ctx = new StlAsciiParser.SolidContext(null, 0);

        Token mockNameToken = mock(Token.class);
        ctx.name = mockNameToken;
        String solidName = "some value";
        when(mockNameToken.getText()).thenReturn(solidName);

        Solid solid = mock(Solid.class);
        when(solidBuilder.generateSolid(solidName)).thenReturn(solid);

        //when
        factory.exitSolid(ctx);

        //then
        verify(solidBuilderFactory, times(2)).newInstance();
        verify(solidsFactory).addSolid(solid);
    }

    @Test
    public void testExitSolids_valuePresent() {
        //given
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory solidsBuilderFactory = mock(SolidsBuilderFactory.class);

        SolidsBuilder solidsFactory = mock(SolidsBuilder.class);
        when(solidsBuilderFactory.newInstance()).thenReturn(solidsFactory);

        SolidsListenerFactory factory = new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory,
                solidBuilderFactory, solidsBuilderFactory);

        StlAsciiParser.SolidsContext ctx = new StlAsciiParser.SolidsContext(null, 0);

        Solids solids = mock(Solids.class);
        when(solidsFactory.generateSolids()).thenReturn(solids);

        //when
        factory.exitSolids(ctx);

        //then
        assertThat(factory.hasSolidsAvailable(), is(true));
        assertThat(factory.getSolids(), is(solids));
    }

    @Test
    public void testExitSolids_valueNotPresent() {
        //given
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory solidsBuilderFactory = mock(SolidsBuilderFactory.class);

        SolidsListenerFactory factory = new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory,
                solidBuilderFactory, solidsBuilderFactory);

        //when
        //exit solids not called

        //then
        assertThat(factory.hasSolidsAvailable(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void testExitSolids_invalidGetThrowsError() {
        //given
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory solidsBuilderFactory = mock(SolidsBuilderFactory.class);

        SolidsListenerFactory factory = new SolidsListenerFactory(vertexBuilder, normalBuilder, facetBuilderFactory,
                solidBuilderFactory, solidsBuilderFactory);

        //when
        factory.getSolids();

        //then
        //exception expected
    }

}