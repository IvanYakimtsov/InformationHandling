package com.yakimtsov.ih.interpreter;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RPNCalculatorTest {
    RPNCalculator rpnCalculator = new RPNCalculator();

    @Test
    public void calculateTest1() {
        String expression = "0 5 - 0 5 - 1 2 / 2 5 2 * + 4 - i 1 + + * + 1200 * + ";
        double expected = 13205.0;
        double actual = rpnCalculator.calculate(expression);
        assertEquals(expected, actual, 0.005);
    }

    @Test
    public void calculateTest2() {
        String expression = "2 3 + 4 + 4 3 * 2 / 2 3 / + 4 4 * 4 * + / ";
        double expected = 7.296296296296297;
        double actual = rpnCalculator.calculate(expression);
        assertEquals(expected, actual, 0.005);
    }

}
