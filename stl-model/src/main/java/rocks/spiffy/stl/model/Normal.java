package rocks.spiffy.stl.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Unit normal vector
 */
@EqualsAndHashCode
@ToString
@Getter
public class Normal {
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal z;

    public Normal(BigDecimal x, BigDecimal y, BigDecimal z) {
        Assert.notNull(x, "normal parameters cannot be null");
        Assert.notNull(y, "normal parameters cannot be null");
        Assert.notNull(z, "normal parameters cannot be null");

        this.x = x;
        this.y = y;
        this.z = z;
    }

}
