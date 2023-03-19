package org.example.robot_example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class Leg implements Runnable {
    private static final AtomicBoolean lock = new AtomicBoolean();
    private final String name;
    private final boolean isActive;

    public Leg(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public void run() {
        while (true) {
//            synchronized (lock) {
                if (lock.get() == isActive) {
                    System.out.println(name);
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                    lock.compareAndSet(isActive, !isActive);
                }
//            }
        }
    }

    public static void main(String[] args) {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(new Leg("left", false)),
                CompletableFuture.runAsync(new Leg("right", true))
        ).join();
    }
}
