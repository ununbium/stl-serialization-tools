package rocks.spiffy.stl.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class VertexTest {

    @Test
    public void testValidConstructor() {
        //given
        BigDecimal x = BigDecimal.ONE;
        BigDecimal y = BigDecimal.ZERO;
        BigDecimal z = BigDecimal.TEN;

        //when
        Vertex v = new Vertex(x, y, z);

        //then
        assertThat(v.getX(), is(x));
        assertThat(v.getY(), is(y));
        assertThat(v.getZ(), is(z));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor_nullX() {
        //given
        BigDecimal x = null;
        BigDecimal y = BigDecimal.ZERO;
        BigDecimal z = BigDecimal.TEN;

        //when
        new Vertex(x, y, z);

        //then
        // exception expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor_nullY() {
        //given
        BigDecimal x = BigDecimal.ONE;
        BigDecimal y = null;
        BigDecimal z = BigDecimal.TEN;

        //when
        Vertex v = new Vertex(x, y, z);

        //then
        // exception expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor_nullZ() {
        //given
        BigDecimal x = BigDecimal.ONE;
        BigDecimal y = BigDecimal.ZERO;
        BigDecimal z = null;

        //when
        new Vertex(x, y, z);

        //then
        // exception expected
    }

}