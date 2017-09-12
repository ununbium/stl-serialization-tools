package rocks.spiffy.stl.parse;

import org.junit.Test;
import rocks.spiffy.stl.Normal;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class NormalTest {

    @Test
    public void testConstruction() {
        //given
        BigDecimal x = mock(BigDecimal.class);
        BigDecimal y = mock(BigDecimal.class);
        BigDecimal z = mock(BigDecimal.class);

        Normal n = new Normal(x, y, z);

        //when
        BigDecimal xResult = n.getX();
        BigDecimal yResult = n.getY();
        BigDecimal zResult = n.getZ();

        //then
        assertThat(xResult, is(xResult));
        assertThat(yResult, is(yResult));
        assertThat(zResult, is(zResult));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullX() {
        //given
        BigDecimal x = null;
        BigDecimal y = mock(BigDecimal.class);
        BigDecimal z = mock(BigDecimal.class);

        //when
        Normal n = new Normal(x, y, z);

        //then
        //exception expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullY() {
        //given
        BigDecimal x = mock(BigDecimal.class);
        BigDecimal y = null;
        BigDecimal z = mock(BigDecimal.class);

        //when
        Normal n = new Normal(x, y, z);

        //then
        //exception expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionNullZ() {
        //given
        BigDecimal x = mock(BigDecimal.class);
        BigDecimal y = mock(BigDecimal.class);
        BigDecimal z = null;

        //when
        Normal n = new Normal(x, y, z);

        //then
        //exception expected
    }

}