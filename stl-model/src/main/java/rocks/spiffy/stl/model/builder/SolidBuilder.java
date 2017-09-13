package rocks.spiffy.stl.model.builder;

import rocks.spiffy.stl.model.Facet;
import rocks.spiffy.stl.model.Solid;

import java.util.ArrayList;
import java.util.List;

/**
 * A stateful builder that allows a solid to be progressively made by an interpreter
 */
public class SolidBuilder {

    private List<Facet> facets = new ArrayList<>();

    /**
     * Add a facet to the solid builder
     *
     * @param facet the facet to add
     */
    public void addFacet(Facet facet) {
        facets.add(facet);
    }

    /**
     * generate a solid with the given name
     *
     * @param name the name of the solid
     */
    public Solid generateSolid(String name) {
        return new Solid(name, facets);
    }
}
