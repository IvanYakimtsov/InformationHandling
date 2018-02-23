package com.yakimtsov.ih.handler;

import com.yakimtsov.ih.composite.*;
import com.yakimtsov.ih.interpreter.RPNCalculator;
import com.yakimtsov.ih.parser.RPNParser;
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

    private static final String LEXEME_REGEXP = "[^\\s]+";
    private static final String FORMULA_REGEXP = "\\(*-?\\d.+?(?=\\s\\w{2,})";


    public void setHandler(TextHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(String text, TextComponent component) {
        Pattern pattern = Pattern.compile(LEXEME_REGEXP);

        RPNParser parser = new RPNParser();

        Pattern formulaPattern = Pattern.compile(FORMULA_REGEXP);

        Matcher matcher = formulaPattern.matcher(text);
        RPNCalculator calculator = new RPNCalculator();

        while (matcher.find()) {
            String formula = matcher.group();
            String rpnFormula = parser.parse(formula);
            Double value = calculator.calculate(rpnFormula);
            text = text.replace(formula, String.valueOf(value));

        }


        matcher = pattern.matcher(text);
        while (matcher.find()) {
            String lexeme = matcher.group();
            TextPart child = new TextPart(TextComponent.TextComponentType.LEXEME);
            component.add(child);
            if (handler != null) {
                handler.handle(lexeme, child);
            }

        }
    }

}
