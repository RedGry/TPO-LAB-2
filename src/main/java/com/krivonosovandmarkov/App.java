package com.krivonosovandmarkov;

import com.krivonosovandmarkov.logariphmic.Ln;
import com.krivonosovandmarkov.logariphmic.Log;
import com.krivonosovandmarkov.trigonometric.Cos;
import com.krivonosovandmarkov.trigonometric.Sin;
import com.krivonosovandmarkov.trigonometric.Tan;

import java.io.IOException;
import java.math.BigDecimal;

public class App {

    public static void main(String[] args) throws IOException {
        final Cos cos = new Cos();
        CsvWriter.write(
                "csv/cos.csv",
                cos,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.001"));

        final Sin sin = new Sin();
        CsvWriter.write(
                "csv/sin.csv",
                sin,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.001"));

        final Tan tan = new Tan();
        CsvWriter.write(
                "csv/tan.csv",
                tan,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.001"));

        final Ln ln = new Ln();
        CsvWriter.write(
                "csv/ln.csv",
                ln,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.001"));

        final Log log3 = new Log(3);
        CsvWriter.write(
                "csv/log3.csv",
                log3,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.001"));

        final Log log5 = new Log(5);
        CsvWriter.write(
                "csv/log5.csv",
                log5,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.001"));

        final Log log10 = new Log(10);
        CsvWriter.write(
                "csv/log10.csv",
                log10,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.001"));
    }
}
