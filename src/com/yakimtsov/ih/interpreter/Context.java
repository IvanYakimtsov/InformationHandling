package com.yakimtsov.ih.interpreter;

import java.util.ArrayDeque;

/**
 * Created by Ivan on 09.02.2018.
 */
public class Context {
    private ArrayDeque<Double> contextValues = new ArrayDeque<>();
    public Double popValue() {
        return contextValues.pop();
    }
    public void pushValue(Double value) {
        this.contextValues.push(value);
    }
}
