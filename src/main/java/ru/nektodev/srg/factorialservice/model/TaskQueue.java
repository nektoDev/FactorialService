package ru.nektodev.srg.factorialservice.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Singleton class object which contains {@link Task} for {@link ru.nektodev.srg.factorialservice.worker.Worker} threads
 * Contains {@link #getTasks()} queue to save tasks for workers and {@link #getResult()} result list to save all tasks
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 26.09.15
 * @see Task
 * @see ru.nektodev.srg.factorialservice.worker.Worker
 * @see BlockingQueue
 */
public class TaskQueue {
    private static TaskQueue instance;
    private static AtomicInteger counter; //need to set Task identifier(to show in results)

    private BlockingQueue<Task> tasks;
    private List<Task> result;

    /**
     * Method get TaskQueue instance
     * @return instance
     */
    public static synchronized TaskQueue getInstance() {
        if (instance == null) {
            instance = new TaskQueue();
        }
        return instance;
    }

    /**
     * Private contructor for TaskQueue. Initialize {@link Task} queue, result list and counter
     */
    private TaskQueue() {
        tasks = new LinkedBlockingQueue<>();
        result = new ArrayList<>();
        counter = new AtomicInteger(0);
    }

    /**
     * Method to add {@link Task} object to queue and result list.
     * @param n number to calculate
     * @see Task
     */
    public synchronized void addTask(BigInteger n) {
        Task t = new Task(counter.getAndIncrement(), n);
        this.tasks.add(t);
        this.result.add(t.getId(), t);
    }

    /**
     * Method to get queue of tasks for workers
     * @return {@link BlockingQueue} queue of tasks
     */
    public synchronized BlockingQueue<Task> getTasks() {
        return tasks;
    }

    /**
     * Method to get all tasks, resulted or not. Should used to see all results and progress
     * @return List of all {@link Task} objects
     */
    public synchronized List<Task> getResult() {
        return result;
    }
}
