package com.yakimtsov.ih.handler.configurator;

import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.exception.IncorrectFileException;
import com.yakimtsov.ih.reader.TextReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class TextComponentParserTest {

    String text;
    TextReader textReader = new TextReader();
    TextComponentParser componentParser = new TextComponentParser();

    @BeforeClass
    public void setUp() {
        try {
            text = textReader.read("Data/text.txt");
        } catch (IncorrectFileException e) {
            fail(e.getMessage());
        }
    }


    @Test
    public void parseComponents(){
        TextComponent component = componentParser.parseComponents(text);
        int expectedSize = 767;
        int actualSize = component.toString().length();
        assertEquals(expectedSize,actualSize);

    }
}
