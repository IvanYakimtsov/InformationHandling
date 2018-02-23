package com.yakimtsov.ih.handler;

import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.composite.TextPart;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ParagraphHandlerTest {
    String input = "\tHi.\n\tThis is new paragraph.";

    @Test
    public void handle(){
        ParagraphHandler paragraphHandler = new ParagraphHandler();
        TextComponent textComponent = new TextPart(TextComponent.TextComponentType.TEXT);
        paragraphHandler.handle(input,textComponent);
        int expectedAmountOfParagraphs = 2;
        int actualAmountOfParagraphs = textComponent.getChildren().size();
        assertEquals(expectedAmountOfParagraphs,actualAmountOfParagraphs);
    }
}
