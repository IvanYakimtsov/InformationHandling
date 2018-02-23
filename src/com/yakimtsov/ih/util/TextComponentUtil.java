package com.yakimtsov.ih.util;

import com.yakimtsov.ih.composite.TextComponent;

import java.util.Comparator;

public class TextComponentUtil {
    public static void sortParagraph(TextComponent textComponent) {
        textComponent.getChildren().sort(Comparator.comparingInt(o -> o.getChildren().size()));
    }

    public static void sortLexeme(TextComponent textComponent) {
        for (TextComponent paragraph : textComponent.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                sentence.getChildren().sort(Comparator.comparingInt(o -> o.toString().length()));
            }
        }
    }

    public static void sortWords(TextComponent textComponent) {

        for (TextComponent paragraph : textComponent.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                sentence.getChildren().sort((firstLexeme, secondLexeme) -> {
                    String firstWord = "";
                    String secondWord = "";

                    for(TextComponent component: firstLexeme.getChildren()){
                        if(component.getType() == TextComponent.TextComponentType.WORD){
                            firstWord = component.toString();
                            break;
                        }
                    }

                    for(TextComponent component: secondLexeme.getChildren()){
                        if(component.getType() == TextComponent.TextComponentType.WORD){
                            secondWord = component.toString();
                            break;
                        }
                    }
                    return firstWord.length() - secondWord.length();
                });
            }
        }
    }

}


