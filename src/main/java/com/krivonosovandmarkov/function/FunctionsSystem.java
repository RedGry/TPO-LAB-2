package com.krivonosovandmarkov.function;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.krivonosovandmarkov.logariphmic.Ln;
import com.krivonosovandmarkov.logariphmic.Log;
import com.krivonosovandmarkov.trigonometric.Sin;
import com.krivonosovandmarkov.trigonometric.Tan;

import java.math.BigDecimal;
import java.math.MathContext;

public class FunctionsSystem implements SeriesExpandableFunction{

  private final Sin sin;
  private final Tan tan;
  private final Ln ln;
  private final Log log3;
  private final Log log5;
  private final Log log10;

  public FunctionsSystem() {
    this.sin = new Sin();
    this.tan = new Tan();
    this.ln = new Ln();
    this.log3 = new Log(3);
    this.log5 = new Log(5);
    this.log10 = new Log(10);
  }

  public BigDecimal calculate(final BigDecimal x, final BigDecimal precision) {
    final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
    final BigDecimal correctedX = x.remainder(BigDecimalMath.pi(mc).multiply(new BigDecimal(2)));
    if (x.compareTo(ZERO) <= 0) {
      return tan.calculate(correctedX, precision)
              .pow(2)
              .pow(3)
              .pow(3)
              .pow(2)
              .pow(3)
              .setScale(precision.scale(), HALF_EVEN);
    } else {
      if (ln.calculate(correctedX, precision).equals(ZERO)) return null;
      return (((log3.calculate(correctedX, precision)
              .multiply(log10.calculate(correctedX, precision))
              .divide(ln.calculate(correctedX, precision), HALF_UP))
              .add(log5.calculate(correctedX, precision)))
              .subtract(log3.calculate(correctedX, precision)))
              .divide((ln.calculate(correctedX, precision).multiply(log10.calculate(correctedX, precision))), HALF_UP)
              .setScale(precision.scale(), HALF_EVEN);
    }
  }
}
