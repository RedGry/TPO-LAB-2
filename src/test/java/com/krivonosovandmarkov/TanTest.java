package com.krivonosovandmarkov;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.MathContext;

import com.krivonosovandmarkov.trigonometric.Cos;
import com.krivonosovandmarkov.trigonometric.Sin;
import com.krivonosovandmarkov.trigonometric.Tan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TanTest {

  private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");

  @Mock private Sin mockSin;
  @Mock private Cos mockCos;
  @Spy private Sin spySin;

  @Test
  void shouldCallSinAndCosFunctions() {
    final Cos cos = new Cos(spySin);
    final Cos spyCos = spy(cos);

    final Tan tan = new Tan(spySin, spyCos);
    tan.calculate(ZERO, DEFAULT_PRECISION);

    verify(spySin, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    verify(spyCos, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
  }

  @Test
  void shouldCalculateWithMockSinAndMockCos() {
    final BigDecimal arg = new BigDecimal(5);

    when(mockSin.calculate(eq(arg), any(BigDecimal.class)))
        .thenReturn(new BigDecimal("-0.95892427"));
    when(mockCos.calculate(eq(arg), any(BigDecimal.class)))
        .thenReturn(new BigDecimal("0.28366218"));

    final Tan tan = new Tan(mockSin, mockCos);
    final BigDecimal expectedResult = new BigDecimal("-3.3805");
    assertEquals(expectedResult, tan.calculate(arg, DEFAULT_PRECISION));
  }

  @Test
  void shouldCalculateWithMockSin() {
    final BigDecimal arg = new BigDecimal(5);

    when(mockSin.calculate(eq(arg), any(BigDecimal.class)))
        .thenReturn(new BigDecimal("-0.95892427"));

    final Tan tan = new Tan(mockSin, new Cos());
    final BigDecimal expectedResult = new BigDecimal("-3.3801");
    assertEquals(expectedResult, tan.calculate(arg, DEFAULT_PRECISION));
  }

  @Test
  void shouldCalculateWithMockCos() {
    final BigDecimal arg = new BigDecimal(5);

    when(mockCos.calculate(eq(arg), any(BigDecimal.class)))
        .thenReturn(new BigDecimal("0.28366218"));

    final Tan tan = new Tan(new Sin(), mockCos);
    final BigDecimal expectedResult = new BigDecimal("-3.3804");
    assertEquals(expectedResult, tan.calculate(arg, DEFAULT_PRECISION));
  }

  @Test
  void shouldCalculateForZero() {
    final Tan tan = new Tan();
    assertEquals(
        ZERO.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN),
        tan.calculate(ZERO, DEFAULT_PRECISION));
  }

  @Test
  void shouldNotCalculateForPiDividedByTwo() {
    final Tan tan = new Tan();
    final MathContext mc = new MathContext(DECIMAL128.getPrecision());
    final BigDecimal arg =
        BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
    assertThrows(ArithmeticException.class, () -> tan.calculate(arg, DEFAULT_PRECISION));
  }

  @Test
  void shouldCalculateForOne() {
    final Tan tan = new Tan();
    final BigDecimal expected = new BigDecimal("1.5575");
    assertEquals(expected, tan.calculate(ONE, DEFAULT_PRECISION));
  }

  @Test
  void shouldCalculateForPeriodic() {
    final Tan tan = new Tan();
    final BigDecimal expected = new BigDecimal("-1.9101");
    assertEquals(expected, tan.calculate(new BigDecimal(134), DEFAULT_PRECISION));
  }
}
