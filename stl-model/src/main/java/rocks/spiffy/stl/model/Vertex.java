package rocks.spiffy.stl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * A point in space identified by x y z co-ordinates
 */
@Data
public class Vertex {

    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal z;

    public Vertex(BigDecimal x, BigDecimal y, BigDecimal z) {
        Assert.notNull(x, "x vertex coordinate cannot be null");
        Assert.notNull(y, "y vertex coordinate cannot be null");
        Assert.notNull(z, "z vertex coordinate cannot be null");

        this.x = x;
        this.y = y;
        this.z = z;
    }
}
