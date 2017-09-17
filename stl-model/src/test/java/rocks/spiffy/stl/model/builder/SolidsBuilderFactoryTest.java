package rocks.spiffy.stl.model.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@PrepareForTest(SolidsBuilderFactory.class)
@RunWith(PowerMockRunner.class)
public class SolidsBuilderFactoryTest {

    @Test
    public void testNewInstance() throws Exception {
        //given
        SolidsBuilderFactory solidsBuilderFactory = new SolidsBuilderFactory();

        SolidsBuilder m = mock(SolidsBuilder.class);
        PowerMockito.whenNew(SolidsBuilder.class).withAnyArguments().thenReturn(m);

        //when
        SolidsBuilder solidsBuilder = solidsBuilderFactory.newInstance();

        //then
        assertThat(solidsBuilder, is(m));
    }

}