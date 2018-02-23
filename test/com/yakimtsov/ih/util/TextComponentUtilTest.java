package com.yakimtsov.ih.util;

import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.exception.IncorrectFileException;
import com.yakimtsov.ih.handler.configurator.TextComponentParser;
import com.yakimtsov.ih.reader.TextReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class TextComponentUtilTest {
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
    public void sortParagraph() {
        TextComponent textComponent = componentParser.parseComponents(text);
        String expected = textComponent.getChildren().get(2).toString();
        TextComponentUtil.sortParagraph(textComponent);
        String actual = textComponent.getChildren().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void sortLexeme() {
        TextComponent textComponent = componentParser.parseComponents(text);
        TextComponentUtil.sortLexeme(textComponent);
        String actual = textComponent.getChildren().get(1)
                .getChildren().get(0)
                .getChildren().get(0).toString();
        String expected = " a";
        assertEquals(expected,actual);
    }


    @Test
    public void sortWords() {
        TextComponent textComponent = componentParser.parseComponents(text);
        TextComponentUtil.sortWords(textComponent);
        String actual = textComponent.getChildren().get(1)
                .getChildren().get(0)
                .getChildren().get(0).toString();
        String expected = " a";
        assertEquals(expected,actual);
    }
}
