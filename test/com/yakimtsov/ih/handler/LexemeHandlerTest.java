package com.yakimtsov.ih.handler;

import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.composite.TextPart;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LexemeHandlerTest {
    String input = "It is a (-5+1/2*(2+5*2- --j))*1200 established.";

    @Test
    public void handle(){
        TextHandler lexemeHandler = new LexemeHandler();
        TextComponent textComponent = new TextPart(TextComponent.TextComponentType.SENTENCE);
        lexemeHandler.handle(input, textComponent);
        int expectedAmount = 5;
        int actualAmount = textComponent.getChildren().size();
        assertEquals(expectedAmount,actualAmount);
    }

}
