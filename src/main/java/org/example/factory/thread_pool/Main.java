package org.example.factory.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static final int NUMBER_OF_WORKER = 8;
    private static final int NUMBER_OF_MACHINES = 5;
    public static ExecutorService machines = Executors.newFixedThreadPool(NUMBER_OF_MACHINES);//5 постоянно работающих станков

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_WORKER; i++) {
            workers.add(new Worker(i));
        }
        List<Future<Worker>> futures = machines.invokeAll(workers);
        while (true) {
            List<Worker> list = new ArrayList<>();
            for (Future<Worker> future : futures) {
                Worker worker = future.get();
                list.add(worker);
            }
            futures = machines.invokeAll(list);
        }
    }
}
