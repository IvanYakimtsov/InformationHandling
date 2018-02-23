package com.yakimtsov.ih.handler;

import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.composite.TextPart;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SentenceHandlerTest {
    String input = "\tTest. Second sentence.";

    @Test
    public void handle() {
        TextHandler sentenceHandler = new SentenceHandler();
        TextComponent textComponent = new TextPart(TextComponent.TextComponentType.PARAGRAPH);
        sentenceHandler.handle(input, textComponent);
        int expectedAmount = 2;
        int actualAmount = textComponent.getChildren().size();
        assertEquals(expectedAmount,actualAmount);
    }
}
