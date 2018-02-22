package com.yakimtsov.ih.handler;

import com.sun.istack.internal.NotNull;
import com.yakimtsov.ih.composite.Symbol;
import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.composite.TextPart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ivan on 07.02.2018.
 */
public class WordHandler implements TextHandler {
    private TextHandler handler;

    private final String LATTER_REGEXP = "[A-Za-z]";
  //  private static final String LATTER_REGEXP = "\\w";
    private static final String PUNCTUATION_MARK_REGEXP = "[!?.\\-,()]";
    private static final String SYMBOL_REGEXP = ".";
    private static final String DIGIT_REGEXP = "\\-?\\d+\\.?\\d+";


    @Override
    public void setHandler(TextHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(String text, TextComponent component) {
        Pattern latterPattern = Pattern.compile(LATTER_REGEXP);
        Pattern punctuationPattern = Pattern.compile(PUNCTUATION_MARK_REGEXP);
        Pattern digitPattern = Pattern.compile(DIGIT_REGEXP);
        Pattern symbolPattern = Pattern.compile(SYMBOL_REGEXP);

        Matcher matcher = symbolPattern.matcher(text);


        Matcher symbolMatcher = digitPattern.matcher(text);
        if (symbolMatcher.matches()) {
            addWord(text, component);
        } else {
            String word = "";
            while (matcher.find()) {
                String symbol = matcher.group();

                symbolMatcher = punctuationPattern.matcher(symbol);
                if (symbolMatcher.matches()) {
                    if (!"".equals(word)) {
                        addWord(word, component);
                        word = "";
                    }
                    addChild(symbol.charAt(0), component, Symbol.SymbolType.PUNCTUATION_MARK);
                }

                symbolMatcher = latterPattern.matcher(symbol);
                if (symbolMatcher.matches()) {
                    word += symbol;
                }
            }

            if (!"".equals(word)) {
                addWord(word, component);
            }
        }

    }


    private void addWord(String word, TextComponent component) {
        TextComponent child = new TextPart(TextPart.TextPartType.WORD);
        component.add(child);
        if (handler != null) {
            handler.handle(word, child);
        }
    }

    private void addChild(char word, TextComponent component, Symbol.SymbolType type) {
        Symbol child = new Symbol(type);
        child.setValue(word);
        component.add(child);
    }

}
