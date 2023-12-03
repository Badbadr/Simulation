package org.bidribidi.simulation.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Predator extends Creature {

    private int hitPower;

    @Override
    public void makeMove() {

    }
}
