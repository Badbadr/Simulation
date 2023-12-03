package org.bidribidi.simulation.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Herbivore extends Creature {
    private int power;

    @Override
    public void makeMove() {

    }
}
