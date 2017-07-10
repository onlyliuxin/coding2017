package com.coderising.myood.uml;

import com.google.common.collect.ImmutableList;

import java.util.Random;

/**
 * Created by thomas_young on 27/6/2017.
 */
public class Dice {

    private ImmutableList<Integer> values = ImmutableList.of(1, 2, 3, 4, 5, 6);

    public int roll() {
       return values.get(new Random().nextInt(values.size()));
    }
}
