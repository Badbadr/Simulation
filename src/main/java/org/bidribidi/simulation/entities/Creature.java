package org.bidribidi.simulation.entities;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Creature extends Entity {
    private int velocity;
    private int healthPoint;

    public abstract void makeMove();
}