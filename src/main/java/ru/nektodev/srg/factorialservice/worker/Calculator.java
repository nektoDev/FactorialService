package ru.nektodev.srg.factorialservice.worker;

import java.math.BigInteger;
import java.util.Random;

/**
 * Simple class to calculate
 *
 * @author Tsykin V.A.
 *         ts.slawa@gmail.com
 * @date 26.09.15
 */
public class Calculator {

    /**
     * Recursively calculate factorial of number n;
     * If n = null, returns 0
     * If n = 0 or 1, returns 1
     *
     * @param n number to calculate if factorial
     * @return n!
     * @throws IllegalArgumentException if n < 0
     */
    public BigInteger factorial(BigInteger n) throws IllegalArgumentException {
        if (n == null) return BigInteger.ZERO;

        if (n.compareTo(BigInteger.ZERO) < 0) throw new IllegalArgumentException("Cannot count factorial for negative numbers");

        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) return BigInteger.ONE;

        return n.multiply(this.factorial(n.add(BigInteger.ONE.negate())));
    }
}
