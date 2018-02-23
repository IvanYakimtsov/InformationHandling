package com.yakimtsov.ih.composite;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Ivan on 05.02.2018.
 */
public interface TextComponent {
    enum TextComponentType {
        TEXT, PARAGRAPH, SENTENCE, LEXEME, WORD, SYMBOL
    }
    void add(TextComponent t);
    void remove(TextComponent t);
    List<TextComponent> getChildren();
    TextComponentType getType();
}
