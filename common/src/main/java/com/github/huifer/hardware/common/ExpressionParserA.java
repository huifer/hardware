package com.github.huifer.hardware.common;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParserA {

    private static final Map<String, String> operators = new HashMap<String, String>() {{
        put("+", "加法");
        put("-", "减法");
        put("*", "乘法");
        put("/", "除法");
        put("sin", "sin");
    }};

    public static Object strToJson(String expression) {
        expression = expression.replace(" ", "");
        return parseHelper(expression);
    }

    private static Object parseHelper(String expression) {
        if (expression == null || expression.length() == 0) {
            return "None";
        }

        if (expression.charAt(0) == '(') {
            int count = 0;
            int end = 0;
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '(') {
                    count++;
                } else if (expression.charAt(i) == ')') {
                    count--;
                }
                if (count == 0) {
                    end = i;
                    break;
                }
            }

            try {
                String leftExpr = null;

                if (end == 0) {

                    leftExpr = expression.substring(1);
                } else {
                    leftExpr = expression.substring(1, end);

                }

                String operator = "None";
                String rightExpr = "None";

                if (end + 1 < expression.length() && operators.containsKey(
                    expression.substring(end + 1, end + 2))) {
                    operator = operators.get(expression.substring(end + 1, end + 2));
                    rightExpr = expression.substring(end + 2);
                }

                HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("left", parseHelper(leftExpr));
                objectObjectHashMap.put("op", operator);
                objectObjectHashMap.put("right", parseHelper(rightExpr));
                return                         "{" +
                    "\"left\": " + parseHelper(leftExpr) + ", " +
                    "\"op\": \"" + operator + "\", " +
                    "\"right\": " + parseHelper(rightExpr) +
                    "}"
                    ;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            for (String operator : operators.keySet()) {
                if (expression.contains(operator)) {
                    if (operator.equals("sin")) {
                        return Map.of(
                            "left", expression.substring(operator.length()),
                            "op", operators.get(operator),
                            "right", expression.substring(operator.length())
                        );
                    } else {
                        String op = operators.get(operator);
                        String[] parts = expression.split("\\Q" + operator + "\\E");
                        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                        objectObjectHashMap.put("left", parseHelper(parts[0]));
                        objectObjectHashMap.put("op", op);
                        objectObjectHashMap.put("right", parseHelper(parts[1]));


                        return                         "{" +
                            "\"left\": " + parseHelper(parts[0]) + ", " +
                            "\"op\": \"" + op + "\", " +
                            "\"right\": " + parseHelper(parts[1]) +
                            "}"
                            ;
                    }
                }
            }

            return "\""+ expression +"\"";
        }
    }

    public static void main(String[] args) {
        String expression = "( ( a + b ) - c ) * 3 / ( a + b )";
        Object result = strToJson(expression);
        System.out.println(result);
    }
}
