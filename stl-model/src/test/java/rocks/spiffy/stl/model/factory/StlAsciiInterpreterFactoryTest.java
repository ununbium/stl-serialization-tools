package rocks.spiffy.stl.model.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rocks.spiffy.stl.model.builder.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

@PrepareForTest(StlAsciiInterpreterFactory.class)
@RunWith(PowerMockRunner.class)
public class StlAsciiInterpreterFactoryTest {

    @Test
    public void testProduce() throws Exception {
        //given
        VertexBuilder vertexBuilder = mock(VertexBuilder.class);
        NormalBuilder normalBuilder = mock(NormalBuilder.class);
        FacetBuilderFactory facetBuilderFactory = mock(FacetBuilderFactory.class);
        SolidBuilderFactory solidBuilderFactory = mock(SolidBuilderFactory.class);
        SolidsBuilderFactory solidsBuilderFactory = mock(SolidsBuilderFactory.class);

        StlAsciiInterpreterFactory interpreterFactory = new StlAsciiInterpreterFactory(vertexBuilder,normalBuilder,
                facetBuilderFactory,solidBuilderFactory,solidsBuilderFactory);

        StlAsciiInterpreter mock = mock(StlAsciiInterpreter.class);

        PowerMockito.whenNew(StlAsciiInterpreter.class).withArguments( eq(vertexBuilder),eq(normalBuilder),
                eq(facetBuilderFactory),eq(solidBuilderFactory),eq(solidsBuilderFactory))
                .thenReturn(mock);

        //when
        StlAsciiInterpreter produce = interpreterFactory.produce();

        //then
        assertThat(produce, is(mock));
    }

}