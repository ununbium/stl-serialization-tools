package rocks.spiffy.stl.parse;

import org.junit.Test;
import rocks.spiffy.stl.Facet;
import rocks.spiffy.stl.Normal;

import java.math.BigDecimal;
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
        List<BigDecimal> vertexes = new ArrayList<>();

        BigDecimal v0 = mock(BigDecimal.class);
        vertexes.add(v0);

        BigDecimal v1 = mock(BigDecimal.class);
        vertexes.add(v1);

        BigDecimal v2 = mock(BigDecimal.class);
        vertexes.add(v2);

        Facet f = new Facet(vertexes, n);

        //when
        List<BigDecimal> vetexesResult = f.getVertexes();

        //then
        assertThat(vetexesResult, is(vertexes));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNotEnoughVetexes() {
        //given
        Normal n = mock(Normal.class);
        List<BigDecimal> vertexes = new ArrayList<>();

        vertexes.add(mock(BigDecimal.class));
        vertexes.add(mock(BigDecimal.class));

        //when
        new Facet(vertexes, n);

        //then
        //exception expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNoVetexes() {
        //given
        Normal n = mock(Normal.class);
        List<BigDecimal> vertexes = new ArrayList<>();

        //when
        new Facet(vertexes, n);

        //then
        //exception expected
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTooManyVetexes() {
        //given
        Normal n = mock(Normal.class);
        List<BigDecimal> vertexes = new ArrayList<>();

        vertexes.add(mock(BigDecimal.class));
        vertexes.add(mock(BigDecimal.class));
        vertexes.add(mock(BigDecimal.class));
        vertexes.add(mock(BigDecimal.class));

        //when
        new Facet(vertexes, n);

        //then
        //exception expected
    }

}