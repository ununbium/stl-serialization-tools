package rocks.spiffy.stl.model;

import org.junit.Test;
import rocks.spiffy.stl.model.Facet;
import rocks.spiffy.stl.model.Normal;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class FacetTest {

    @Test
    public void testConstructorParametersRetrievable() {
        //given
        Normal n = mock(Normal.class);
        List<Vertex> vertexes = new ArrayList<>();

        Vertex v0 = mock(Vertex.class);
        vertexes.add(v0);

        Vertex v1 = mock(Vertex.class);
        vertexes.add(v1);

        Vertex v2 = mock(Vertex.class);
        vertexes.add(v2);

        Facet f = new Facet(vertexes, n);

        //when
        List<Vertex> vetexesResult = f.getVertexes();

        //then
        assertThat(vetexesResult, is(vertexes));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNotEnoughVetexes() {
        //given
        Normal n = mock(Normal.class);
        List<Vertex> vertexes = new ArrayList<>();

        vertexes.add(mock(Vertex.class));
        vertexes.add(mock(Vertex.class));

        //when
        new Facet(vertexes, n);

        //then
        //exception expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNoVetexes() {
        //given
        Normal n = mock(Normal.class);
        List<Vertex> vertexes = new ArrayList<>();

        //when
        new Facet(vertexes, n);

        //then
        //exception expected
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTooManyVetexes() {
        //given
        Normal n = mock(Normal.class);
        List<Vertex> vertexes = new ArrayList<>();

        vertexes.add(mock(Vertex.class));
        vertexes.add(mock(Vertex.class));
        vertexes.add(mock(Vertex.class));
        vertexes.add(mock(Vertex.class));

        //when
        new Facet(vertexes, n);

        //then
        //exception expected
    }

}