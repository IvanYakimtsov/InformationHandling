package com.yakimtsov.ih.parser;

import java.util.*;

/**
 * Created by Ivan on 08.02.2018.
 */
public class RPNParser {
    private final String OPERATORS = "+-*/";
    private final String VARIABLES = "ij";
    private ArrayDeque<String> stackOperations = new ArrayDeque<>();
    private ArrayDeque<String> stackRPN = new ArrayDeque<>();


    public String parse(String expression) {
        stackOperations.clear();
        stackRPN.clear();

        expression = expression.replace("i++", "(i+1)").replace("++i", "(i+1)")
                .replace("i--", "(i-1)").replace("--i", "(i-1)")
                .replace("j++", "(j+1)").replace("++j", "(j+1)")
                .replace("j--", "(j-1)").replace("--j", "(j-1)")
                .replace(" ", "").replace("(-", "(0-");
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(expression,
                OPERATORS + "()", true);

        //Shunting-yard algorithm https://en.wikipedia.org/wiki/Shunting-yard_algorithm
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isOpenBracket(token)) {
                stackOperations.push(token);
            } else if (isCloseBracket(token)) {
                while (!stackOperations.isEmpty()
                        && !isOpenBracket(stackOperations.getFirst())) {
                    stackRPN.push(stackOperations.pop());
                }
//                if (!stackOperations.isEmpty()) {
//                    stackOperations.pop();
//                } else {
//                    throw new InvalidParametersException("missing close brackets");
//                }
                stackOperations.pop();

            } else if (isNumber(token)) {
                stackRPN.push(token);
            } else if (isOperator(token)) {
                while (!stackOperations.isEmpty()
                        && isOperator(stackOperations.getFirst())
                        && getPrecedence(token) <= getPrecedence(stackOperations
                        .getFirst())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.push(token);
            }
        }
        while (!stackOperations.isEmpty()) {
            stackRPN.push(stackOperations.pop());
        }

        StringBuilder result = new StringBuilder();
        Iterator iterator = stackRPN.descendingIterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            result.append(str + " ");
        }

//        if (result.toString().contains("(")) {
//            throw new InvalidParametersException("missing open brackets");
//        }
        return result.toString();
    }


    private boolean isNumber(String token) {

        return new Scanner(token).hasNextDouble() || VARIABLES.contains(token);
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
