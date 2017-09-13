package rocks.spiffy.stl.model;

import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * A simplification of the underlying file format, every facet is assumed to be the regular (three a vertex one loop)
 * simple triangle.
 */
@EqualsAndHashCode
public class Facet {
    private final List<Vertex> vertexes;
    private final Normal normal;

    public Facet(List<Vertex> vertexes, Normal normal) {
        Assert.isTrue(vertexes.size()==3, "unexpected number of vertexes");
        this.vertexes = vertexes;
        this.normal = normal;
    }

    public List<Vertex> getVertexes() {
        return Collections.unmodifiableList(vertexes);
    }

}
