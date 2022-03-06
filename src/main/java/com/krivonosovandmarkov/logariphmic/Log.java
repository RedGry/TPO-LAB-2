package com.krivonosovandmarkov.logariphmic;

import com.krivonosovandmarkov.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Log extends Calculator {

    private final Ln ln;
    private final int base;

    public Log(int accuracy, int base, Ln ln) {
        super(accuracy);
        this.base = base;
        this.ln = ln;
    }

    @Override
    public BigDecimal calc(double x) {
        return ln.calc(x)
                .divide(ln.calc(this.base), RoundingMode.HALF_UP)
                .setScale(getAccuracy(), RoundingMode.HALF_EVEN);
    }

}
