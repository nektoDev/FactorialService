package ru.nektodev.srg.factorialservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nektodev.srg.factorialservice.model.TaskQueue;
import ru.nektodev.srg.factorialservice.worker.Worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class FactorialServiceApplication {

    /**
     * Main method. Initialize thread pool and Spring application
     * @param args
     */
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        TaskQueue queue = TaskQueue.getInstance();

        for (int i = 0; i<10; i++ ) {
            Worker w = new Worker(queue.getTasks());
            service.submit(w);
        }

        SpringApplication.run(FactorialServiceApplication.class, args);
    }
}
