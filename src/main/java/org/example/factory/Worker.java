package org.example.factory;

import java.util.concurrent.Callable;

public class Worker implements Callable<Void> {
    private int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public Void call() throws Exception {
        workOnMachine(id);
        Main.machines.submit(this);
        return null;
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
