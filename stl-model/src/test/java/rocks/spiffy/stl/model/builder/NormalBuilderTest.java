package rocks.spiffy.stl.model.builder;

import org.junit.Test;
import rocks.spiffy.stl.model.Normal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NormalBuilderTest {

    @Test
    public void testValidConstruct() {
        //given

        NormalBuilder builder = new NormalBuilder();

        //when
        Normal result = builder.fromStrings("-1", "6.88338e16", "-6.88338e-16");

        //then
        assertThat(result.getX().toPlainString(), is("-1"));
        assertThat(result.getY().toPlainString(), is("68833800000000000"));
        assertThat(result.getZ().toPlainString(), is("-0.000000000000000688338"));
    }

    @Test(expected = NumberFormatException.class)
    public void testBadRawX() {
        //given

        NormalBuilder builder = new NormalBuilder();

        //when
        builder.fromStrings("bad value", "6.88338e16", "-6.88338e-16");

        //then
        // expect exception
    }

    @Test(expected = NumberFormatException.class)
    public void testBadRawY() {
        //given

        NormalBuilder builder = new NormalBuilder();

        //when
        builder.fromStrings("6.88338e16", "bad value", "-6.88338e-16");

        //then
        // expect exception
    }

    @Test(expected = NumberFormatException.class)
    public void testBadRawZ() {
        //given

        NormalBuilder builder = new NormalBuilder();

        //when
        builder.fromStrings("6.88338e16", "-6.88338e-16", "bad value");

        //then
        // expect exception
    }

}