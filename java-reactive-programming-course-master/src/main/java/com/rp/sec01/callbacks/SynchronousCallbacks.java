package com.rp.sec01.callbacks;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SynchronousCallbacks {

    @NonNull
    private TaskListener taskListener;

    public void doMandateTask() {
        System.out.println("Under doMandateTask()");
        taskListener.doTask();
        System.out.println("Waiting for above listener task to complete");
    }

    public static void main(String[] args) {
        ImplementationConsumer implementationConsumer = new ImplementationConsumer();
        implementationConsumer.doTaskSync();
    }
}
