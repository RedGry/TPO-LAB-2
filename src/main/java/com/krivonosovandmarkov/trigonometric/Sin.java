package com.krivonosovandmarkov.trigonometric;

import com.krivonosovandmarkov.Calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Sin extends Calculator {

    public Sin(int accuracy) {
        super(accuracy);
    }

    @Override
    public BigDecimal calc(double x) {
        double PI2 = Math.PI * 2;
        int i = 0;
        BigDecimal sum = new BigDecimal(0), prev;

        if (x >= 0) {
            while (x > PI2) {
                x -= PI2;
            }
        } else if (x < 0){
            while (x < PI2) {
                x += PI2;
            }
        }

        do {
            prev = sum;
            sum = sum.add(minusOnePow(i).multiply(prod(x, 2 * i + 1)));
            i++;
        } while (new BigDecimal("0.1").pow(getAccuracy()).compareTo(prev.subtract(sum).abs()) < 0);


        return sum.setScale(getAccuracy(), RoundingMode.HALF_EVEN);
    }

    private static BigDecimal minusOnePow(int n){
        return BigDecimal.valueOf(1 - (n % 2) * 2);
    }

    private static BigDecimal prod(double x, int n){
        BigDecimal accum = new BigDecimal(1);

        for (int i = 1; i <= n; i++){
            accum = accum.multiply( new BigDecimal(x / i));
        }

        return accum;
    }

}
