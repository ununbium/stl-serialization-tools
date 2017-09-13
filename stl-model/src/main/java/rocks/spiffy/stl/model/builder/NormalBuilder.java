package rocks.spiffy.stl.model.builder;

import org.springframework.stereotype.Component;
import rocks.spiffy.stl.model.Normal;
import rocks.spiffy.stl.model.Normal;

import java.math.BigDecimal;

/**
 * Allows a Normal to be progressively built
 */
@Component
public class NormalBuilder {

    /**
     * set the coordinates to the parameters provided
     *
     * @param rawTextX
     * @param rawTextY
     * @param rawTextZ
     */
    public Normal fromStrings(String rawTextX, String rawTextY, String rawTextZ) {
        BigDecimal x = new BigDecimal(rawTextX);
        BigDecimal y = new BigDecimal(rawTextY);
        BigDecimal z = new BigDecimal(rawTextZ);

        return new Normal(x,y,z);
    }

}
