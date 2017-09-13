package rocks.spiffy.stl.model.builder;

import org.springframework.util.Assert;
import rocks.spiffy.stl.model.Facet;
import rocks.spiffy.stl.model.Normal;
import rocks.spiffy.stl.model.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * a stateful builder that accumulates vertices until it has three, at which point the facet can be generated
 */
public class FacetBuilder {
    public List<Vertex> vertices = new ArrayList<>();

    /**
     * add a new vertex
     *
     * @param v the vertex to add
     */
    public void addVertex(Vertex v) {
        Assert.notNull(v, "vertex cannot be null");
        if(vertices.size()>2) {
            throw new IllegalStateException("cannot have more than 3 vertices for a facet");
        }

        vertices.add(v);
    }

    /**
     * generate a facet from a given normal and the previously supplied vertexes
     *
     * Note that it's possible if the number of vertices is less than three for this method to throw an error.
     * This is intentional - the parser grammar is written such that it should not be possible to have less or more than
     * three vertices - any other operation is likely a fault of the grammar or interpreter.
     *
     * @param n the normal for this facet
     * @return a full representation of this facet
     */
    public Facet generateFacet(Normal n) {
        int size = vertices.size();
        if(size !=3) {
            throw new IllegalStateException("cannot have facet with " + size + " vertices");
        }

        return new Facet(vertices, n);
    }
}
