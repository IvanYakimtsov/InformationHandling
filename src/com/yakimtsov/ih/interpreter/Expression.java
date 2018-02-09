package com.yakimtsov.ih.interpreter;

/**
 * Created by Ivan on 09.02.2018.
 */
@FunctionalInterface
public interface Expression {
    void interpret(Context c);
}
