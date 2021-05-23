package com.rp.sec01.callbacks;

public class ImplementationConsumer {

    public void doTaskSync(){
        SynchronousCallbacks synchronousCallbacks = new SynchronousCallbacks(() -> {
            try {
                System.out.println("Work in Progress");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        synchronousCallbacks.doMandateTask();
    }

    public void doTaskAsync(){
        AsynchronousCallbacks asynchronousCallbacks = new AsynchronousCallbacks(() -> {
            try {
                System.out.println("Work in Progress");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        asynchronousCallbacks.doMandateTask();
    }
}
