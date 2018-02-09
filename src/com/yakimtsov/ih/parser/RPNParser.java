package com.yakimtsov.ih.parser;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Ivan on 08.02.2018.
 */
public class RPNParser {
    private final String OPERATORS = "+-*/";
    private final String VARIABLES = "ij";
 //   private final String NUVBER_REGEX = "\d"
    private Stack<String> stackOperations = new Stack<>();
    private Stack<String> stackRPN = new Stack<>();


    public String parse(String expression) {
        // cleaning stacks
        stackOperations.clear();
        stackRPN.clear();

        // make some preparations
        expression = expression.replace("i++", "(i+1)").replace("++i", "(i+1)")
                .replace("i--", "(i-1)").replace("--i", "(i-1)")
                .replace("j++", "(j+1)").replace("++j", "(j+1)")
                .replace("j--", "(j-1)").replace("--j", "(j-1)")
                .replace(" ", "").replace("(-", "(0-");
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        // splitting input string into tokens
        StringTokenizer stringTokenizer = new StringTokenizer(expression,
                OPERATORS + "()", true);

        // loop for handling each token - shunting-yard algorithm
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isOpenBracket(token)) {
                stackOperations.push(token);
            } else if (isCloseBracket(token)) {
                while (!stackOperations.empty()
                        && !isOpenBracket(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.pop();
                if (!stackOperations.empty()) {
                    stackRPN.push(stackOperations.pop());
                }
            } else if (isNumber(token)) {
                stackRPN.push(token);
            } else if (isOperator(token)) {
                while (!stackOperations.empty()
                        && isOperator(stackOperations.lastElement())
                        && getPrecedence(token) <= getPrecedence(stackOperations
                        .lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.push(token);
            }
        }
        while (!stackOperations.empty()) {
            stackRPN.push(stackOperations.pop());
        }

        StringBuilder result = new StringBuilder();
        stackRPN.forEach(c -> {result.append(c + " ");});
   //     System.out.println(result.toString());
        return result.toString();
    }


    private boolean isNumber(String token) {
        //TODO: fix it
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            return VARIABLES.contains(token);
        }
        return true;
    }


    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private byte getPrecedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }

}
