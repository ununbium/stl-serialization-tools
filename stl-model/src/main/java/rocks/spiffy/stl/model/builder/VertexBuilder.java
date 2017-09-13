package rocks.spiffy.stl.model.builder;

import org.springframework.stereotype.Component;
import rocks.spiffy.stl.model.Vertex;

import java.math.BigDecimal;

/**
 * Allows a vertex to be progressively built
 */
@Component
public class VertexBuilder {

    /**
     * set the coordinates to the parameters provided
     *
     * @param rawTextX
     * @param rawTextY
     * @param rawTextZ
     */
    public Vertex fromStrings(String rawTextX, String rawTextY, String rawTextZ) {
        BigDecimal x = new BigDecimal(rawTextX);
        BigDecimal y = new BigDecimal(rawTextY);
        BigDecimal z = new BigDecimal(rawTextZ);

        return new Vertex(x,y,z);
    }

}
