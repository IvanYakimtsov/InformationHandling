package com.yakimtsov.ih.handler;

import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.composite.TextPart;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class WordHandlerTest {
    String input = "established.";

    @Test
    public void handle(){
        TextHandler wordHandler = new WordHandler();
        TextComponent textComponent = new TextPart(TextComponent.TextComponentType.LEXEME);
        wordHandler.handle(input, textComponent);
        int expectedAmount = 2;
        int actualAmount = textComponent.getChildren().size();
        assertEquals(expectedAmount,actualAmount);
    }
}
