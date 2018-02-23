package com.yakimtsov.ih;

import com.yakimtsov.ih.handler.configurator.TextComponentParser;
import com.yakimtsov.ih.composite.TextComponent;
import com.yakimtsov.ih.exception.IncorrectFileException;
import com.yakimtsov.ih.reader.TextReader;
import com.yakimtsov.ih.util.TextComponentUtil;

import java.util.Comparator;

/**
 * Created by Ivan on 07.02.2018.
 */
public class Main {

    public static void main(String[] args) {
        TextReader textReader = new TextReader();
        TextComponentParser componentParser = new TextComponentParser();
        try {
            String textString = textReader.read("Data/text.txt");
            TextComponent text = componentParser.parseComponents(textString);
            System.out.println(text.toString());

            TextComponentUtil.sortParagraph(text);
            System.out.println(text.toString());

            TextComponentUtil.sortWords(text);
            System.out.println(text.toString());

            TextComponentUtil.sortLexeme(text);
            System.out.println(text.toString());


        } catch (IncorrectFileException e) {
            e.printStackTrace();
        }
    }
}
