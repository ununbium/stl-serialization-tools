package spiffy.rocks.stapler.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An stl solid
 */
@EqualsAndHashCode
public class Solid {

    @Getter
    private final String name;
    private final List<Facet> facets = new ArrayList<>();

    public Solid(String name, List<Facet> facets) {
        Assert.notNull(name, "name cannot be null");
        Assert.notNull(facets, "facets cannot be null");

        this.name = name;
        this.facets.addAll(facets);
    }

    public List<Facet> getFacets() {
        return Collections.unmodifiableList(facets);
    }

}
