package com.krivonosovandmarkov.logariphmic;

import com.krivonosovandmarkov.Calculator;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ln extends Calculator {

    public static final int MAX_ITERATIONS = 10000;

    public Ln(int accuracy) {
        super(accuracy);
    }

    @Override
    @SneakyThrows
    public BigDecimal calc(double x){
        if (Double.isNaN(x) || x < 0.0){
            throw new ArithmeticException(String.format("Function value for argument %s doesn't exist", x));
        } else if ( x == Double.POSITIVE_INFINITY) {
            return null;
        } else if (x == 0.0) {
            return null;
        }

        BigDecimal curValue = BigDecimal.ZERO, prevValue;
        int i = 1;

        if (Math.abs(x - 1) <= 1){
            do {
                prevValue = curValue;
                curValue =  curValue.add(
                        (
                                (BigDecimal.valueOf(-1).pow(i - 1))
                                        .multiply(BigDecimal.valueOf(x - 1).pow(i))
                        )
                                .divide(BigDecimal.valueOf(i), getAccuracy(), RoundingMode.HALF_UP)
                );
                i++;
            } while (new BigDecimal("0.1").pow(getAccuracy()).compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < MAX_ITERATIONS);
            return curValue.add(prevValue).divide(BigDecimal.valueOf(2), RoundingMode.HALF_EVEN);
        } else {
            do {
                prevValue = curValue;
                curValue =  curValue.add(
                        (
                                BigDecimal.valueOf(-1).pow(i - 1)
                                        .divide(BigDecimal.valueOf(x - 1).pow(i), getAccuracy(), RoundingMode.HALF_UP)
                        )
                                .divide(BigDecimal.valueOf(i), getAccuracy(), RoundingMode.HALF_UP)
                );
                i++;
            } while (new BigDecimal("0.1").pow(getAccuracy()).compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < MAX_ITERATIONS);

            curValue = curValue.add(calc(x - 1));
        }

        return curValue.setScale(getAccuracy(), RoundingMode.HALF_EVEN);
    }

}
