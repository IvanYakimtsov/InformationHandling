package com.yakimtsov.ih.handler;

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

    private final String PUNCTUATION_BEFORE_WORD_REGEXP = "[(<{\\[]+";
    private final String PUNCTUATION_AFTER_WORD_REGEXP = "[)>},\\.\\]?!]+";
    private final String WORD_IN_LEXEME_REGEXP = "(\\w+|-)";
    private static final String NUMBER_REGEXP = "-?\\d+\\.?\\d+";


    @Override
    public void setHandler(TextHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(String text, TextComponent component) {
        Pattern numberPattern = Pattern.compile(NUMBER_REGEXP);
        Pattern beforeWordPunctuation = Pattern.compile(PUNCTUATION_BEFORE_WORD_REGEXP);
        Pattern afterWordPunctuation = Pattern.compile(PUNCTUATION_AFTER_WORD_REGEXP);
        Pattern wordPattern = Pattern.compile(WORD_IN_LEXEME_REGEXP);
        Matcher matcher = numberPattern.matcher(text);

        if (matcher.matches()) {
            TextComponent child = new TextPart(TextComponent.TextComponentType.WORD);
            component.add(child);
            if (handler != null) {
                handler.handle(text, child);
            }
        } else {
            matcher = beforeWordPunctuation.matcher(text);
            if (matcher.find()) {
                addPunctuation(matcher,component);
            }
            String word = "";
            TextComponent wordComponent = null;
            matcher = wordPattern.matcher(text);
            if (matcher.find()){
                word = matcher.group();
                wordComponent = new TextPart(TextComponent.TextComponentType.WORD);
                component.add(wordComponent);
            }

            matcher = afterWordPunctuation.matcher(text);
            if(matcher.find()){
                addPunctuation(matcher,component);
            }


            if (handler != null && wordComponent != null) {
                handler.handle(word, wordComponent);
            }
        }


    }

    private void addPunctuation(Matcher matcher, TextComponent component){
        String punctuation = matcher.group();
        for(int index = 0; index<punctuation.length(); index++){
            Symbol child = new Symbol(TextComponent.TextComponentType.SYMBOL);
            child.setValue(punctuation.charAt(index));
            component.add(child);
        }
    }


}
