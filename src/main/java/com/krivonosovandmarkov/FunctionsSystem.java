package com.krivonosovandmarkov;

import com.krivonosovandmarkov.logariphmic.Ln;
import com.krivonosovandmarkov.logariphmic.Log;
import com.krivonosovandmarkov.trigonometric.Tan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionsSystem extends Calculator{

    private final Tan tan;
    private final Ln ln;
    private final Log log3;
    private final Log log5;
    private final Log log10;


    public FunctionsSystem(int accuracy, Tan tan, Ln ln, Log log3, Log log5, Log log10) {
        super(accuracy);
        this.tan = tan;
        this.ln = ln;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    @Override
    public BigDecimal calc(double x) {
        if (x <= 0){
            return tan.calc(x).pow(2).pow(3).pow(3).pow(2).pow(3)
                    .setScale(getAccuracy(), RoundingMode.HALF_EVEN);
        } else {
            if (ln.calc(x).doubleValue() == 0) throw new ArithmeticException("Division by Zero!");
            return (((log3.calc(x).multiply(log10.calc(x)).divide(ln.calc(x), RoundingMode.HALF_UP))
                    .add(log5.calc(x)))
                    .subtract(log3.calc(x)))
                    .divide((ln.calc(x).multiply(log10.calc(x))), RoundingMode.HALF_UP)
                    .setScale(getAccuracy(), RoundingMode.HALF_EVEN);
        }
    }

}
