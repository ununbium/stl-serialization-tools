package spiffy.rocks.stapler.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SolidsTest {

    @Test
    public void testConstructorParametersAreGettable() {
        //given
        List<Solid> l = new ArrayList<>();
        Solid s1 = mock(Solid.class);
        l.add(s1);

        Solid s2 = mock(Solid.class);
        l.add(s2);

        Solids s = new Solids(l);

        //when
        List<Solid> solids = s.getSolids();

        //then
        assertThat(solids.get(0), is(s1));
        assertThat(solids.get(1), is(s2));
    }

    @Test
    public void testEmptyListIsAccepted() {
        //given
        List<Solid> l = new ArrayList<>();

        Solids s = new Solids(l);

        //when
        List<Solid> solids = s.getSolids();

        //then
        assertThat(solids.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void testNullListExceptions() {
        //given

        //when
        Solids s = new Solids(null);

        //then
        //exception expected
    }
}