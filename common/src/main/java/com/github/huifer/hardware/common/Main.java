package com.github.huifer.hardware.common;

import java.util.HashMap;

public class Main {

  public static HashMap<String, String> operators = new HashMap<>();

  static {
    // 定义操作符和对应的中文名称
    operators.put("+", "加法");
    operators.put("-", "减法");
    operators.put("*", "乘法");
    operators.put("/", "除法");
    operators.put("sin", "sin");
  }

  public static String strToJson(String expression) {
    // 去除字符串中的空格
    expression = expression.replace(" ", "");

    return parseHelper(expression);
  }

  public static String parseHelper(String expression) {
    if (expression == null || expression.length() == 0) {
      return "None";
    }

    if (expression.charAt(0) == '(') {
      // 找到表达式的最外层括号的结束位置
      int count = 0;
      int end = -1;
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

      // 提取括号内的表达式，并递归解析

      String leftExpr = null;
      try {
        if (end == -1) {
          end = expression.length();
        }
        leftExpr = expression.substring(1, end);
      } catch (Exception e) {
        e.printStackTrace();
      }

      String operator = "None";
      String rightExpr = "None";

      // 查找最外层括号后面的操作符
      if (end + 1 < expression.length() && operators.containsKey(
          String.valueOf(expression.charAt(end + 1)))) {
        operator = operators.get(String.valueOf(expression.charAt(end + 1)));
        rightExpr = expression.substring(end + 2);
      }

      return "{" +
          "\"left\": " + parseHelper(leftExpr) + ", " +
          "\"op\": \"" + operator + "\", " +
          "\"right\": " + parseHelper(rightExpr) +
          "}";
    } else {
      // 如果表达式不包含括号，则说明是最小单位，可以是变量或者函数
      String operator = "None";
      String[] parts = new String[2];

      // 查找操作符
      for (String key : operators.keySet()) {
        if (expression.contains(key)) {
          // 如果是函数，则将函数名作为操作符，剩余部分作为参数
          if (key.equals("sin")) {
            parts[0] = expression.substring(key.length());
            operator = operators.get(key);
            parts[1] = expression.substring(key.length());
          }
          // 否则，将操作符作为运算符，将表达式分割成左右两部分，并递归解析
          else {
            operator = operators.get(key);

            parts = expression.split("\\Q" + key + "\\E");
          }
          break;
        }
      }

      // 如果没有操作符，则说明是变量
      if (operator.equals("None")) {
        return "\"" + expression + "\"";
      }

      return "{" +
          "\"left\": " + parseHelper(parts[0]) + ", " +
          "\"op\": \"" + operator + "\", " +
          "\"right\": " + parseHelper(parts[1]) +
          "}";
    }
  }

  public static void main(String[] args) {
    String expression = "a+b-c*ba+(a+c)";
    String json = strToJson(expression);
    System.out.println(json);
  }
}