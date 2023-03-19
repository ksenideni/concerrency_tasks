package org.example.factory.synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    private static final int NUMBER_OF_WORKER = 8;


    public static void main(String[] args) throws InterruptedException {
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_WORKER; i++) {
            workers.add(new Worker(i));
        }
        int j = 0;
        while (true) {
            Worker.SEMAPHORE.acquire();
            System.out.println("acquire" + j);
            new Thread(workers.get(j)).start();
            System.out.println("release" + j);
            j = j == NUMBER_OF_WORKER - 1 ? 0 : j + 1;
        }

    }
}
