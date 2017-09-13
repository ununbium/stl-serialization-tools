package rocks.spiffy.stl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * represents a top level collection of solids
 */
public class Solids {
    private final List<Solid> solids = new ArrayList<>();

    public Solids(List<Solid> solids) {
        this.solids.addAll(solids);
    }

    public List<Solid> getSolids() {
        return Collections.unmodifiableList(solids);
    }
}
