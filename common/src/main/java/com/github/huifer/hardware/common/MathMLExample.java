package com.github.huifer.hardware.common;

import org.scilab.forge.jlatexmath.TeXFormula;

public class MathMLExample {

    public static void main(String[] args) throws Exception {
        double a = 3.0;
        double b = 4.0;
        double c = 2.0;

//        String latex = "\\frac{" + a + "+" + b + "}{" + c + "}";
        String latex = "(" + a + "+" + b + ")" +"*2";
        System.out.println(latex);
        TeXFormula formula = new TeXFormula(latex);
        System.out.println();
        double result = formula.createTeXIcon(1, 1).getIconWidth();
        System.out.println(result);
    }
}
