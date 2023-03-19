package org.example.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NUMBER_OF_WORKER = 8;
    private static final int NUMBER_OF_MACHINES = 5;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService machines = Executors.newFixedThreadPool(NUMBER_OF_MACHINES);//5 постоянно работающих станков
        List<Callable<Void>> workers = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_WORKER; i++) {
            workers.add(createWorker(i));
        }
        machines.invokeAll(workers);
        machines.shutdown();
    }

    private static void workOnMachine(int workerId) {
        try {
            System.out.println("worker" + workerId + " occupy production machine...");
            Thread.sleep(2000);
            System.out.println("worker" + workerId + " release production machine");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Callable<Void> createWorker(int workerId) {
        return () -> {
            workOnMachine(workerId);
            return null;
        };
    }
}