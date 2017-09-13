package rocks.spiffy.stl.model;

import org.junit.Test;
import rocks.spiffy.stl.model.Facet;
import rocks.spiffy.stl.model.Solid;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SolidTest {

    @Test
    public void testConstructorParametersGettable() {
        //given
        String name = "123";
        List<Facet> facets = new ArrayList<>();

        Facet f1 = mock(Facet.class);
        facets.add(f1);

        Facet f2 = mock(Facet.class);
        facets.add(f2);

        Solid s = new Solid(name, facets);

        //when
        String nameResult = s.getName();
        List<Facet> facetsResult = s.getFacets();

        //then
        assertThat(nameResult, is(name));
        assertThat(2, is(facetsResult.size()));
        assertThat(f1, is(facetsResult.get(0)));
        assertThat(f2, is(facetsResult.get(1)));
    }

    @Test
    public void testConstructorParametersGettableEmptyList() {
        //given
        String name = "123";
        List<Facet> facets = new ArrayList<>();

        Solid s = new Solid(name, facets);

        //when
        String nameResult = s.getName();
        List<Facet> facetsResult = s.getFacets();

        //then
        assertThat(nameResult, is(name));
        assertThat(0, is(facetsResult.size()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRetrievedListNotModifiable() {
        //given
        String name = "123";
        List<Facet> facets = new ArrayList<>();

        Solid s = new Solid(name, facets);
        List<Facet> facetsResultList = s.getFacets();

        Facet f1 = mock(Facet.class);

        //when
        facetsResultList.add(f1);

        //then
        //error expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullNameIsRejected() {
        //given
        String name = null;
        List<Facet> facets = new ArrayList<>();

        Facet f1 = mock(Facet.class);
        facets.add(f1);

        //when
        new Solid(name, facets);

        //then
        //expected exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullListIsRejected() {
        //given
        String name = "123";
        List<Facet> facets = null;

        //when
        new Solid(name, facets);

        //then
        //expected exception
    }

}