package spiffy.rocks.stapler.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

/**
 * Unit normal vector
 */
@EqualsAndHashCode
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
