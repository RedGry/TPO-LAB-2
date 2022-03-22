package com.krivonosovandmarkov;

import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import com.krivonosovandmarkov.function.SeriesExpandableFunction;
import com.krivonosovandmarkov.logariphmic.Ln;
import com.krivonosovandmarkov.logariphmic.Log;
import com.krivonosovandmarkov.trigonometric.Cos;
import com.krivonosovandmarkov.trigonometric.Sin;
import com.krivonosovandmarkov.trigonometric.Tan;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SeriesExpandableFunctionTest {

  private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.000001");

  @ParameterizedTest
  @MethodSource("functions")
  void shouldNotAcceptNullArgument(final SeriesExpandableFunction function) {
    assertThrows(NullPointerException.class, () -> function.calculate(null, DEFAULT_PRECISION));
  }

  @ParameterizedTest
  @MethodSource("functions")
  void shouldNotAcceptNullPrecision(final SeriesExpandableFunction function) {
    assertThrows(NullPointerException.class, () -> function.calculate(ONE, null));
  }

  private static Stream<Arguments> functions() {
    return Stream.of(
        Arguments.of(new Sin()),
        Arguments.of(new Cos()),
        Arguments.of(new Tan()),
        Arguments.of(new Ln()),
        Arguments.of(new Log(3)),
        Arguments.of(new Log(5)),
        Arguments.of(new Log(10)));
  }
}
