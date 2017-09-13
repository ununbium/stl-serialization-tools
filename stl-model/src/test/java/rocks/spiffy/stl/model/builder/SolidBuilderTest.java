package rocks.spiffy.stl.model.builder;

import org.junit.Test;
import rocks.spiffy.stl.model.Facet;
import rocks.spiffy.stl.model.Solid;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SolidBuilderTest {

    @Test
    public void testGenerate() {
        //given
        SolidBuilder sb = new SolidBuilder();

        Facet f1 = mock(Facet.class);
        sb.addFacet(f1);

        String name = "some name";

        //when
        Solid solid = sb.generateSolid(name);

        //then
        assertThat(solid.getName(), is(name));
        assertThat(solid.getFacets().size(), is(1));
        assertThat(solid.getFacets().get(0), is(f1));
    }

    @Test
    public void testGenerateEmpty() {
        //given
        SolidBuilder sb = new SolidBuilder();

        String name = "some name";

        //when
        Solid solid = sb.generateSolid(name);

        //then
        assertThat(solid.getName(), is(name));
        assertThat(solid.getFacets().size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateNullName_isError() {
        //given
        SolidBuilder sb = new SolidBuilder();

        Facet f1 = mock(Facet.class);
        sb.addFacet(f1);

        //when
        sb.generateSolid(null);

        //then
        //error expected
    }
}