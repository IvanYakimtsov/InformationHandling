package com.yakimtsov.ih.interpreter;

import java.util.ArrayDeque;

/**
 * Created by Ivan on 09.02.2018.
 */
public class Context extends ArrayDeque{
    private ArrayDeque<Double> contextValues = new ArrayDeque<>();
    public Double pop() {
        return contextValues.pop();
    }
    public void push(Double value) {
        this.contextValues.push(value);
    }
}
