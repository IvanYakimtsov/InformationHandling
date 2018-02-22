package com.yakimtsov.ih.composite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ivan on 05.02.2018.
 */
public class Symbol implements TextComponent {
    public static enum SymbolType {
        PUNCTUATION_MARK, SYMBOL//, DIGIT
    }

    private char value;
    private SymbolType type;

    public Symbol(SymbolType type) {
        this.type = type;
    }

    public char getValue() {
       return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public void add(TextComponent t) {

    }

    @Override
    public void remove(TextComponent t) {

    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>();
    }

//    @Override
//    public void sort(Comparator<TextComponent> comparator) {
//
//    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


}
