package com.krivonosovandmarkov.logariphmic;

import com.krivonosovandmarkov.function.LimitedIterationsExpandableFunction;

import static java.lang.String.format;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;
import java.util.Objects;

public class Ln extends LimitedIterationsExpandableFunction {

  public Ln() {
    super();
  }

  @Override
  public BigDecimal calculate(final BigDecimal x, final BigDecimal precision)
      throws ArithmeticException {
    Objects.requireNonNull(x, "Function argument can not be null");
    Objects.requireNonNull(precision, "Precision can not be null");
    if (precision.compareTo(ZERO) <= 0 || precision.compareTo(ONE) >= 0) {
      throw new ArithmeticException("Precision must be less than one and more than zero");
    }
    if (x.compareTo(ZERO) <= 0) {
      throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
    }
    if (x.compareTo(ONE) == 0) {
      return ZERO;
    }

    BigDecimal sum = ZERO;
    BigDecimal previousSum;
    int i = 1;
    do {
      if (i > maxIterations) {
        throw new ArithmeticException(
            "Precision can not be reached with specified max iterations. Max value is " + sum);
      }
      previousSum = sum;
      sum =
          sum.add(
              x.subtract(ONE)
                  .divide(x.add(ONE), DECIMAL128.getPrecision(), HALF_EVEN)
                  .pow(2 * i - 1)
                  .divide(new BigDecimal(2 * i - 1), DECIMAL128.getPrecision(), HALF_EVEN));
      i++;
    } while (sum.subtract(previousSum).abs().compareTo(precision) >= 0);
    sum = sum.multiply(new BigDecimal(2));
    return sum.setScale(precision.scale(), HALF_EVEN);
  }
}
