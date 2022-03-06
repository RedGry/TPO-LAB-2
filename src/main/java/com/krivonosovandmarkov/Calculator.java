package com.krivonosovandmarkov;

import lombok.Data;
import java.math.BigDecimal;

@Data
public abstract class Calculator {

    private int accuracy;

    public Calculator(int accuracy) {
        this.accuracy = accuracy;
    }

    public abstract BigDecimal calc(double x);
}
