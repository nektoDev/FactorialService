package ru.nektodev.srg.factorialservice.model;

import java.math.BigInteger;

/**
 * Model class for tasks to calculate
 * Contains:
 * id - task identifier
 * n - number to calculate
 * result - result of task calculation
 *
 * @author Tsykin V.A.
 *         ts.slawa@gmail.com
 * @date 26.09.15
 * @see TaskQueue
 */
public class Task {
    private int id;
    private BigInteger n;
    private BigInteger result;

    /**
     * Constructor of Task model class. You cannot change Id and N after Task construction
     * @param id - task identificator
     * @param n - number to calculate
     */
    public Task(int id, BigInteger n) {
        this.id = id;
        this.n = n;
        this.result = null;
    }

    public BigInteger getResult() {
        return result;
    }

    public void setResult(BigInteger result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public BigInteger getN() {
        return n;
    }
}
