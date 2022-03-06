package com.krivonosovandmarkov.trigonometric;

import com.krivonosovandmarkov.Calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Cos extends Calculator {

    private final Sin sin;

    public Cos(int accuracy, Sin sin) {
        super(accuracy);
        this.sin = sin;
    }

    @Override
    public BigDecimal calc(double x) {
        int fl = 1;

        if ((x > Math.PI/2) & (x < 3 * Math.PI/2))
            fl = -1;
        if ((x < -Math.PI/2) & (x > -3 * Math.PI/2))
            fl = -1;

        return new BigDecimal(1)
                .subtract(sin.calc(x).multiply(sin.calc(x)))
                .sqrt(new MathContext(getAccuracy(), RoundingMode.HALF_UP))
                .multiply(BigDecimal.valueOf(fl)).setScale(getAccuracy(), RoundingMode.HALF_EVEN);
    }

}
