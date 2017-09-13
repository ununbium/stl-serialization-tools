package rocks.spiffy.stl.model.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@PrepareForTest(SolidBuilderFactory.class)
@RunWith(PowerMockRunner.class)
public class SolidBuilderFactoryTest {

    @Test
    public void testNewInstance() throws Exception {
        //given
        SolidBuilderFactory solidBuilderFactory = new SolidBuilderFactory();

        SolidBuilder m = mock(SolidBuilder.class);
        PowerMockito.whenNew(SolidBuilder.class).withAnyArguments().thenReturn(m);

        //when
        SolidBuilder solidBuilder = solidBuilderFactory.newInstance();

        //then
        assertThat(solidBuilder, is(m));
    }

}