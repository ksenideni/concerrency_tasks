package org.example.factory.thread_pool;

import java.util.concurrent.Callable;

public class Worker implements Callable<Worker> {
    private int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public Worker call() throws Exception {
        workOnMachine(id);
        return this;
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
