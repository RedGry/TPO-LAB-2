package com.krivonosovandmarkov;

import com.krivonosovandmarkov.logariphmic.Ln;
import com.krivonosovandmarkov.logariphmic.Log;
import com.krivonosovandmarkov.trigonometric.Cos;
import com.krivonosovandmarkov.trigonometric.Sin;
import com.krivonosovandmarkov.trigonometric.Tan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class App
{
    public static void main( String[] args ){

        int acc = 20;

        Sin s = new Sin(acc);
        Cos c = new Cos(acc, s);
        Tan t = new Tan(acc, s, c);
        Ln ln = new Ln(acc);
        Log log3 = new Log(acc, 3, ln);
        Log log5 = new Log(acc, 5, ln);
        Log log10 = new Log(acc, 10, ln);

        FunctionsSystem funMy = new FunctionsSystem(acc, t, ln, log3, log5, log10);

        System.out.printf("Sin: %.10f \t| %.10f \n", s.calc(-1), Math.sin(-1));
        System.out.printf("Cos: %.10f \t| %.10f \n", c.calc(-1), Math.cos(-1));
        System.out.printf("Tan: %.10f \t| %.10f \n", t.calc(-1), Math.tan(-1));

        System.out.printf("Ln: %.10f \t| %.10f \n", ln.calc(1.01), Math.log(1.01));
        System.out.printf("Log3: %.10f \t| %.10f \n", log3.calc(1.5), Math.log(1.5) / Math.log(3));
        System.out.printf("Log5: %.10f \t| %.10f \n", log5.calc(1.5), Math.log(1.5) / Math.log(5));
        System.out.printf("Log10: %.10f \t| %.10f \n", log10.calc(1.5), Math.log(1.5) / Math.log(10));

        double x = 50;

        double myTest = (((Math.log(x) / Math.log(3)) * (Math.log(x) / Math.log(10)) / Math.log(x)) + Math.log(x) / Math.log(5)
                - (Math.log(x) / Math.log(3))) / (Math.log(x) * Math.log(x) / Math.log(10));

        System.out.printf("MyFun(x <= 0): %.10e \t| %.10e \n", funMy.calc(-3), Math.pow(Math.tan(-3), 108));
        System.out.printf("MyFun(x > 0) : %.10e \t| %.10e \n", funMy.calc(x), myTest);

    }

}
