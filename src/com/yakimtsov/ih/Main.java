package com.yakimtsov.ih;

import com.yakimtsov.ih.handler.configurator.TextComponentParser;
import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.exception.IncorrectFileException;
import com.yakimtsov.ih.reader.TextReader;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Ivan on 07.02.2018.
 */
public class Main {

    public static void main(String[] args) {
        TextReader textReader = new TextReader();
        TextComponentParser componentParser = new TextComponentParser();
        try {
            String text = textReader.read("Data/text.txt");
            TextComponent parseComponents = componentParser.parseComponents(text);
//            parseComponents.sort(Comparator.comparingInt(o -> o.getChildren().size())); //количество предложений
//            System.out.println(parseComponents.execute());
//            for(TextComponent paragraph : parseComponents.getChildren()){ //lexeme
//                paragraph.sort((o1, o2) -> {
//                    int o1Size = 0;
//                    int o2Size = 0;
//                    for(TextComponent lexeme : o1.getChildren()){
//                       for(TextComponent word : lexeme.getChildren()){
//                           o1Size += word.getChildren().size();
//                       }
//                    }
//
//                    for(TextComponent lexeme : o2.getChildren()){
//                        for(TextComponent word : lexeme.getChildren()){
//                            o2Size += word.getChildren().size();
//                        }
//                    }
//
//                    return o2Size - o1Size;
//                });
//
////                for (TextComponent paragraph : parseComponents.getChildren()) {
////                    for (TextComponent sentence : paragraph.getChildren()) {
////                        sentence.s
////                    }
////                }
//            }
            System.out.println(parseComponents.toString());

        } catch (IncorrectFileException e) {

        }
    }
}
