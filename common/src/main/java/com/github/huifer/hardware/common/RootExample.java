package com.github.huifer.hardware.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.mariuszgromada.math.mxparser.*;
import org.mariuszgromada.math.mxparser.Expression;
import org.mvel2.MVEL;

public class RootExample {

    public static void main(String[] args) {
        String expression = "sqrt(25)"; // 开根号的表达式
        Expression e = new Expression(expression);
        double result = e.calculate();
        System.out.println("sqrt(25) = " + result);

        String expression2 = "java.lang.Math.sqrt(x + y)";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 2);
        variables.put("y", 3);
        System.out.println(MVEL.eval(expression2, variables)); // 输出 5

        hh();
        h2();
        h3();
    }

    public static void h3(){
        net.objecthunter.exp4j.Expression expression = new ExpressionBuilder("3x+2y")
            .variables("x", "y")
            .build()
            .setVariable("x", 2)
            .setVariable("y", 3);

        double result = expression.evaluate();
        System.out.println(result);
    }

    public static void h2() {
        String formula = "Math.sqrt((x + 2) * Math.pow(2, x) * Math.log10(x) /1) + Math.E";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 4.0);
        Serializable compiledExpression = MVEL.compileExpression(formula);
        double result = (double) MVEL.executeExpression(compiledExpression, variables);
        System.out.println(result); // 输出 3.281300771179675
    }

    public static void hh() {
        String expression = "Math.asin(x)";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 0.5);
        Object eval = MVEL.eval(expression, variables);

        System.out.println("result: " + eval);
    }
}
