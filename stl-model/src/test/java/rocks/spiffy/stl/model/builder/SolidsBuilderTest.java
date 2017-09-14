package rocks.spiffy.stl.model.builder;

import org.junit.Test;
import rocks.spiffy.stl.model.Solid;
import rocks.spiffy.stl.model.Solids;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SolidsBuilderTest {
    @Test
    public void testGenerate() {
        //given
        SolidsBuilder sb = new SolidsBuilder();

        Solid s1 = mock(Solid.class);
        sb.addSolid(s1);

        //when
        Solids solids = sb.generateSolids();

        //then
        assertThat(solids.getSolids().size(), is(1));
        assertThat(solids.getSolids().get(0), is(s1));
    }

    @Test
    public void testGenerateEmpty() {
        //given
        SolidsBuilder sb = new SolidsBuilder();

        //when
        Solids solids = sb.generateSolids();

        //then
        assertThat(solids.getSolids().size(), is(0));
    }
}