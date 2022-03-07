package com.krivonosovandmarkov;

import com.krivonosovandmarkov.logariphmic.Ln;
import com.krivonosovandmarkov.logariphmic.Log;
import com.krivonosovandmarkov.trigonometric.Cos;
import com.krivonosovandmarkov.trigonometric.Sin;
import com.krivonosovandmarkov.trigonometric.Tan;

import java.io.IOException;

public class App
{
    public static void main(String[] args) throws IOException {

        final int ACCURACY = 20;

        final Sin sin = new Sin(ACCURACY);
        CsvWriter.write(
                "csv/sin.csv",
                sin,
                -1.0,
                1.0,
                0.1);

        final Cos cos = new Cos(ACCURACY, sin);
        CsvWriter.write(
                "csv/cos.csv",
                cos,
                -1.0,
                1.0,
                0.1);

        final Tan tan = new Tan(ACCURACY, sin, cos);
        CsvWriter.write(
                "csv/tan.csv",
                tan,
                -1.0,
                1.0,
                0.1);

        final Ln ln = new Ln(ACCURACY);
        CsvWriter.write(
                "csv/ln.csv",
                ln,
                1.0,
                4.0,
                1.0);

        final Log log3 = new Log(ACCURACY, 3, ln);
        CsvWriter.write(
                "csv/log3.csv",
                log3,
                1.0,
                4.0,
                1.0);

        final Log log5 = new Log(ACCURACY, 5, ln);
        CsvWriter.write(
                "csv/log5.csv",
                log5,
                1.0,
                4.0,
                1.0);

        final Log log10 = new Log(ACCURACY, 10, ln);
        CsvWriter.write(
                "csv/log10.csv",
                log10,
                1.0,
                4.0,
                1.0);


        final FunctionsSystem my = new FunctionsSystem(ACCURACY, tan, ln, log3, log5, log10);
        CsvWriter.write(
                "csv/my.csv",
                my,
                -1.0,
                1.0,
                0.25);

    }

}
