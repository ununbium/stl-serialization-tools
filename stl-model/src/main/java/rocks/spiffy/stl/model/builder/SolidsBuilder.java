package rocks.spiffy.stl.model.builder;

import rocks.spiffy.stl.model.Solid;
import rocks.spiffy.stl.model.Solids;

import java.util.ArrayList;
import java.util.List;

/**
 * A stateful builder that allows a solid to be progressively made by an interpreter
 */
public class SolidsBuilder {

    private List<Solid> solids = new ArrayList<>();

    /**
     * Add a solid to the solids builder
     *
     * @param solid the solid to add
     */
    public void addSolid(Solid solid) {
        solids.add(solid);
    }

    /**
     * generate a solids
     */
    public Solids generateSolids() {
        return new Solids(solids);
    }
}
