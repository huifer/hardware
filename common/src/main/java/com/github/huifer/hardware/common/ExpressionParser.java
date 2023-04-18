package com.github.huifer.hardware.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExpressionParser {

    public static String parseExpression(JsonNode expression) {
        // 如果 expression 是一个数字或变量，直接返回
        if (expression.isNumber()) {
            return expression.toString();
        } else if (expression.isTextual()) {
            return expression.asText();
        }

        JsonNode left = expression.get("left");
        JsonNode right = expression.get("right");
        String op = expression.get("op").asText();

        // 判断操作符
        if ("加法".equals(op)) {
            return parseExpression(left) + " + " + parseExpression(right);
        } else if ("减法".equals(op)) {
            return parseExpression(left) + " - " + parseExpression(right);
        } else if ("乘法".equals(op)) {
            return parseExpression(left) + " * " + parseExpression(right);
        } else if ("除法".equals(op)) {
            return parseExpression(left) + " / " + parseExpression(right);
        } else if ("幂".equals(op)) {
            return parseExpression(left) + " ** " + parseExpression(right);
        } else if ("sqrt".equals(op)) {
            return "sqrt(" + parseExpression(left) + ", " + parseExpression(right) + ")";
        } else if ("sin".equals(op)) {
            return "sin(" + parseExpression(right) + ")";
        } else if ("取模".equals(op)) {
            return parseExpression(left) + " % " + parseExpression(right);
        } else if ("绝对值".equals(op)) {
            return "abs(" + parseExpression(right) + ")";
        } else if ("cos".equals(op)) {
            return "cos(" + parseExpression(right) + ")";
        } else if ("tan".equals(op)) {
            return "tan(" + parseExpression(right) + ")";
        } else if ("asin".equals(op)) {
            return "asin(" + parseExpression(right) + ")";
        } else if ("acos".equals(op)) {
            return "acos(" + parseExpression(right) + ")";
        } else if ("atan".equals(op)) {
            return "atan(" + parseExpression(right) + ")";
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        // 示例 JSON 字符串
        String jsonStr = "{\"left\":{\"left\":\"a\",\"op\":\"乘法\",\"right\":\"2\"},\"op\":\"加法\",\"right\":{\"left\":\"a\",\"op\":\"幂\",\"right\":{\"left\":\"b\",\"op\":\"sqrt\",\"right\":\"b\"}}}}";

        // 解析 JSON 字符串为 JsonNode 对象
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expression = objectMapper.readTree(jsonStr);

        // 调用函数解析表达式
        String result = parseExpression(expression);

        // 打印结果
        System.out.println(result);
    }
}
