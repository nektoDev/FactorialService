package ru.nektodev.srg.factorialservice.worker;

import ru.nektodev.srg.factorialservice.model.Task;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Worker thread class.
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 26.09.15
 */
public class Worker extends Thread {
    private Calculator calculator;
    private BlockingQueue<Task> queue;

    /**
     * Worker object constructor. Initialize {@link Calculator} and {@link Task} queue
     * @param queue queue which should contains tasks to work
     */
    public Worker(BlockingQueue<Task> queue) {
        this.calculator = new Calculator();
        this.queue = queue;
    }

    /**
     * This method get {@link Task} object from queue and calculate factorial for it using {@link Calculator}
     */
    @Override
    public void run() {
        super.run();

        try {
            while (true) {
                Task t = queue.take();

                try {
                    BigInteger result = calculator.factorial(t.getN());
                    t.setResult(result);
                } catch (IllegalArgumentException e) { //if error appear we should write to result 0.
                    t.setResult(BigInteger.ZERO);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
