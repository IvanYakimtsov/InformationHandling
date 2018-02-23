package com.yakimtsov.ih.handler;

import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.composite.TextPart;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SymbolHandlerTest {
    String input = "Cat";

    @Test
    public void handle(){
        TextHandler symbolHandler = new SymbolHandler();
        TextComponent textComponent = new TextPart(TextComponent.TextComponentType.WORD);
        symbolHandler.handle(input, textComponent);
        int expectedAmount = 3;
        int actualAmount = textComponent.getChildren().size();
        assertEquals(expectedAmount,actualAmount);
    }
}
