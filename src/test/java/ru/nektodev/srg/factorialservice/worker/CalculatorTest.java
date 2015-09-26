package ru.nektodev.srg.factorialservice.worker;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Simple tests for calculator
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 26.09.15
 */
public class CalculatorTest extends TestCase {
    Calculator calculator = new Calculator();

    public void testFactorial() throws Exception {

        assertEquals(calculator.factorial(BigInteger.ZERO), BigInteger.ONE);
        assertEquals(calculator.factorial(BigInteger.ONE), BigInteger.ONE);
        assertEquals(calculator.factorial(BigInteger.TEN), BigInteger.valueOf(3628800l));

        assertEquals(calculator.factorial(null), BigInteger.ZERO);
    }

    public void testFactorialException() {
        boolean throwsIllegalArgument = false;
        try {
            calculator.factorial(BigInteger.TEN.negate());
        } catch (IllegalArgumentException e) {
            throwsIllegalArgument = true;
        }

        assertEquals(throwsIllegalArgument, true);
    }

}