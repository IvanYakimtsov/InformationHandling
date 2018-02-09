package com.yakimtsov.ih.handler;

import com.yakimtsov.ih.composite.*;
import com.yakimtsov.ih.interpreter.RPNCalculator;
import com.yakimtsov.ih.parser.RPNParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ivan on 07.02.2018.
 */
public class LexemeHandler implements TextHandler {
    private static Logger logger = LogManager.getLogger();
    private TextHandler handler;

    private final String LEXEME_REGEXP = "[^\\s]+";
    private final String FORMULA_REGEXP = "\\(*-?\\d.+?(?=\\s\\w{2,})";




    public void setHandler(TextHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(String text, TextComponent component) {
        Pattern pattern = Pattern.compile(LEXEME_REGEXP);
        //TODO: handler null check
        //TODO: calculate formula
        RPNParser parser = new RPNParser();

        Pattern formulaPattern = Pattern.compile(FORMULA_REGEXP);

        Matcher matcher = formulaPattern.matcher(text);
        RPNCalculator calculator = new RPNCalculator();

        while (matcher.find()){
            String formula = matcher.group();
       //     System.out.println(parser.parse(formula));
            String rpnFormula = parser.parse(formula);
            Double value = calculator.calculate(rpnFormula);
            text = text.replace(formula,String.valueOf(value));
        }


    //    String pureText = text.replaceAll(FORMULA_REGEXP,"2018");

        matcher = pattern.matcher(text);
        while (matcher.find()) {
            String lexeme = matcher.group();
            TextPart child = new TextPart(TextPart.TextPartType.LEXEME);
            component.add(child);
            handler.handle(lexeme,child);
        }
    }

}
