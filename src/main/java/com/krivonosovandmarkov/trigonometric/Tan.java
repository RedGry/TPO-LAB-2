package com.krivonosovandmarkov.trigonometric;

import com.krivonosovandmarkov.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tan extends Calculator {

    private final Sin sin;
    private final Cos cos;

    public Tan(int accuracy, Sin sin, Cos cos) {
        super(accuracy);
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public BigDecimal calc(double x) {
        return sin.calc(x)
                .divide(cos.calc(x), RoundingMode.HALF_UP)
                .setScale(getAccuracy(), RoundingMode.HALF_EVEN);
    }

}
