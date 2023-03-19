package org.example.factory.synchronizers;

import java.util.concurrent.Semaphore;

public class Worker implements Runnable {

    private static final int NUMBER_OF_MACHINES = 5;
     static final Semaphore SEMAPHORE =
            new Semaphore(NUMBER_OF_MACHINES, true);
    private int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        workOnMachine(id);
        SEMAPHORE.release();
    }

    private void workOnMachine(int workerId) {
        try {
            System.out.println("worker" + workerId + " occupy production machine...");
            Thread.sleep(2000);
            System.out.println("worker" + workerId + " release production machine");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
