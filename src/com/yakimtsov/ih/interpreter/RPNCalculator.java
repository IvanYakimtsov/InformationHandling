package com.yakimtsov.ih.interpreter;


import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Ivan on 09.02.2018.
 */
public class RPNCalculator {
    private final int I = 10;
    private final int J = 20;

    public Double calculate(String expression) {
        Context context = new Context();
        ArrayDeque<Expression> listExpression = parse(expression);
        for (Expression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }

    private ArrayDeque<Expression> parse(String expression) {
        expression = expression.replace("i", String.valueOf(I)).replace("j", String.valueOf(J));
        //System.out.println(expression);
        ArrayDeque<Expression> listExpression = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            switch (token) {
                case "+":
                    listExpression.add((c -> c.pushValue(c.popValue() + c.popValue())));
                    break;
                case "-":
                    listExpression.add((c -> c.pushValue(c.popValue() - c.popValue())));
                    break;
                case "*":
                    listExpression.add((c -> c.pushValue(c.popValue() * c.popValue())));
                    break;
                case "/":
                    listExpression.add((c -> c.pushValue(c.popValue() / c.popValue())));
                    break;
                default:
                    Scanner scan = new Scanner(token);
                    if (scan.hasNextDouble()) {
                        listExpression.add(c -> c.pushValue(scan.nextDouble()));
                    }
            }
        }
        return listExpression;
    }
}
