package org.bidribidi.simulation.entities;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Grass extends Entity implements Eatable {
    public boolean eaten = false;

    @Override
    public boolean eaten() {
        this.eaten = true;
        return true;
    }
}
