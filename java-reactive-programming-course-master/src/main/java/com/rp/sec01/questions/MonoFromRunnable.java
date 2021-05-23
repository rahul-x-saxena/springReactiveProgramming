package com.rp.sec01.questions;

import com.rp.courseutil.DefaultSubscriber;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {

    private static Mono<Integer> get(){
        return Mono.fromRunnable(() -> {
            int a = 1*2;
        });
    }

    private static Mono<Integer> getFromSupplier(){
        return Mono.fromSupplier(() -> 1);
    }

    public static void main(String[] args) {
        MonoFromRunnable.get()
                .log()
                .subscribe(new DefaultSubscriber());

        MonoFromRunnable.getFromSupplier()
                .log()
                .subscribe(new DefaultSubscriber());
    }
}
