package rocks.spiffy.stl.model.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;

@PrepareForTest(FacetBuilderFactory.class)
@RunWith(PowerMockRunner.class)
public class FacetBuilderFactoryTest {

    @Test
    public void testNewInstance() throws Exception {
        //given

        FacetBuilderFactory factory = new FacetBuilderFactory();

        FacetBuilder mockBuilder = mock(FacetBuilder.class);
        PowerMockito.whenNew(FacetBuilder.class).withAnyArguments().thenReturn(mockBuilder);

        //when
        FacetBuilder facetBuilder = factory.newInstance();

        //then
        assertThat(facetBuilder, is(mockBuilder));
    }


}