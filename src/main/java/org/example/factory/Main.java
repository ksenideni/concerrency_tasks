package org.example.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NUMBER_OF_WORKER = 8;
    private static final int NUMBER_OF_MACHINES = 5;
    public static ExecutorService machines = Executors.newFixedThreadPool(NUMBER_OF_MACHINES);//5 постоянно работающих станков

    public static void main(String[] args) {
        for (int i = 0; i < NUMBER_OF_WORKER; i++) {
            machines.submit(new Worker(i));
        }
    }

}
