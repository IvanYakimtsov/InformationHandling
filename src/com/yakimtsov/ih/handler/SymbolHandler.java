package com.yakimtsov.ih.handler;

import com.yakimtsov.ih.composite.Symbol;
import com.yakimtsov.ih.composite.TextComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ivan on 08.02.2018.
 */
public class SymbolHandler implements TextHandler {
    private static final String SYMBOL_REGEXP = ".";

    @Override
    public void setHandler(TextHandler handler) {

    }

    @Override
    public void handle(String text, TextComponent component) {
        Pattern letterPattern = Pattern.compile(SYMBOL_REGEXP);
        Matcher letterMatcher = letterPattern.matcher(text);
        while (letterMatcher.find()){
            String letter = letterMatcher.group();
            Symbol child = new Symbol(TextComponent.TextComponentType.SYMBOL);
            child.setValue(letter.charAt(0));
            component.add(child);
        }
    }
}
