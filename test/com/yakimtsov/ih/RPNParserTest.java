package com.yakimtsov.ih;

import com.yakimtsov.ih.exception.InvalidParametersException;
import com.yakimtsov.ih.parser.RPNParser;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class RPNParserTest {
    RPNParser parser = new RPNParser();

    @Test
    public void parsePositiveTest1() {
        String testString = "-5+(-5+1/2*((2+5*2-4)+ i++))*1200";
        String expected = "0 5 - 0 5 - 1 2 / 2 5 2 * + 4 - i 1 + + * + 1200 * + ";
        String actualString = null;
        try {
            actualString = parser.parse(testString);
        } catch (InvalidParametersException e) {
            fail(e.getMessage());
        }
        assertEquals(actualString, expected);

    }

    @Test
    public void parsePositiveTest2() {
        String testString = "((2+3)+4)/(((4*3/2)+(2/3))+(4*4*4))";
        String expected = "2 3 + 4 + 4 3 * 2 / 2 3 / + 4 4 * 4 * + / ";
        String actualString = null;
        try {
            actualString = parser.parse(testString);
        } catch (InvalidParametersException e) {
            fail(e.getMessage());
        }
        assertEquals(actualString, expected);

    }

    @Test(expectedExceptions = InvalidParametersException.class)
    public void parseNegativeTest1() throws InvalidParametersException{
        String testString = "1+2)";
        parser.parse(testString);
    }

    @Test(expectedExceptions = InvalidParametersException.class)
    public void parseNegativeTest2() throws InvalidParametersException{
        String testString = "(1+2";
        System.out.println(parser.parse(testString));
    }

}
