package com.rp.sec01.callbacks;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AsynchronousCallbacks {
    @NonNull
    private TaskListener taskListener;

    public void doMandateTask() {
        System.out.println("Under doMandateTask()");
        Runnable runnable = () -> {
            taskListener.doTask();
        };
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Not Waiting for above listener task to complete");
    }

    public static void main(String[] args) {
        ImplementationConsumer implementationConsumer = new ImplementationConsumer();
        implementationConsumer.doTaskAsync();
    }
}
