package com.yakimtsov.ih.util;

import com.yakimtsov.ih.composite.TextComponent;

import java.util.Comparator;

public class TextComponentUtil {
    public static void sortParagraph(TextComponent textComponent) {
        if (textComponent.getType() == TextComponent.TextComponentType.TEXT) {
            textComponent.getChildren().sort(Comparator.comparingInt(o -> o.getChildren().size()));
        }
    }

    public static void sortLexeme(TextComponent textComponent) {
        if (textComponent.getType() == TextComponent.TextComponentType.TEXT) {
            for (TextComponent paragraph : textComponent.getChildren()) {
                for (TextComponent sentence : paragraph.getChildren()) {
                    sentence.getChildren().sort(Comparator.comparingInt(o -> o.toString().length()));
                }
            }
        }
    }

    public static void sortWords(TextComponent textComponent) {
        if (textComponent.getType() == TextComponent.TextComponentType.TEXT) {
            for (TextComponent paragraph : textComponent.getChildren()) {
                for (TextComponent sentence : paragraph.getChildren()) {
                    sentence.getChildren().sort((firstLexeme, secondLexeme) -> {
                        //TODO: fix it
                        int firstWordSize = firstLexeme.getChildren()
                                .stream()
                                .filter(lexemeComponent -> lexemeComponent.getType() == TextComponent.TextComponentType.WORD)
                                .mapToInt(lexemeComponent -> lexemeComponent.toString().length()).sum();
                        int secondWordSize = secondLexeme.getChildren()
                                .stream()
                                .filter(lexemeComponent -> lexemeComponent.getType() == TextComponent.TextComponentType.WORD)
                                .mapToInt(lexemeComponent -> lexemeComponent.toString().length()).sum();
                        return firstWordSize - secondWordSize;
                    });
                }
            }
        }
    }
}


