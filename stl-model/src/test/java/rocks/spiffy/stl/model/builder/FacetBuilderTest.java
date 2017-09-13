package rocks.spiffy.stl.model.builder;

import org.junit.Test;
import rocks.spiffy.stl.model.Facet;
import rocks.spiffy.stl.model.Normal;
import rocks.spiffy.stl.model.Vertex;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class FacetBuilderTest {

    @Test
    public void testAddThreeFacets() {
        //given
        FacetBuilder facetBuilder = new FacetBuilder();

        Vertex v1 = mock(Vertex.class);
        Vertex v2 = mock(Vertex.class);
        Vertex v3 = mock(Vertex.class);

        facetBuilder.addVertex(v1);
        facetBuilder.addVertex(v2);
        facetBuilder.addVertex(v3);

        Normal normal = mock(Normal.class);
        //when
        Facet facet = facetBuilder.generateFacet(normal);

        //then
        List<Vertex> verticesResult = facet.getVertexes();

        assertThat(verticesResult.get(0), is(v1));
        assertThat(verticesResult.get(1), is(v2));
        assertThat(verticesResult.get(2), is(v3));
        assertThat(facet.getNormal(), is(normal));
    }

    @Test(expected = IllegalStateException.class)
    public void testAddTwoFacets_isError() {
        //given
        FacetBuilder facetBuilder = new FacetBuilder();

        Vertex v1 = mock(Vertex.class);
        Vertex v2 = mock(Vertex.class);

        facetBuilder.addVertex(v1);
        facetBuilder.addVertex(v2);

        Normal normal = mock(Normal.class);

        //when
        facetBuilder.generateFacet(normal);

        //then
        //error expected
    }

    @Test(expected = IllegalStateException.class)
    public void testAddOneFacets_isError() {
        //given
        FacetBuilder facetBuilder = new FacetBuilder();

        Vertex v1 = mock(Vertex.class);

        facetBuilder.addVertex(v1);

        Normal normal = mock(Normal.class);

        //when
        facetBuilder.generateFacet(normal);

        //then
        //error expected
    }

    @Test(expected = IllegalStateException.class)
    public void testAddNoFacets_isError() {
        //given
        FacetBuilder facetBuilder = new FacetBuilder();

        Normal normal = mock(Normal.class);

        //when
        facetBuilder.generateFacet(normal);

        //then
        //error expected
    }

    @Test
    public void testNullNormal_isError() {
        //given
        FacetBuilder facetBuilder = new FacetBuilder();

        Vertex v1 = mock(Vertex.class);
        Vertex v2 = mock(Vertex.class);
        Vertex v3 = mock(Vertex.class);

        facetBuilder.addVertex(v1);
        facetBuilder.addVertex(v2);
        facetBuilder.addVertex(v3);

        Normal normal = null;
        //when
        facetBuilder.generateFacet(normal);

        //then
        //error expected
    }

    @Test(expected = IllegalStateException.class)
    public void testAddFourFacets_isError() {
        //given
        FacetBuilder facetBuilder = new FacetBuilder();

        Vertex v1 = mock(Vertex.class);
        Vertex v2 = mock(Vertex.class);
        Vertex v3 = mock(Vertex.class);
        Vertex v4 = mock(Vertex.class);

        facetBuilder.addVertex(v1);
        facetBuilder.addVertex(v2);
        facetBuilder.addVertex(v3);

        //when
        facetBuilder.addVertex(v4);

        //then
        //error expected
    }

}