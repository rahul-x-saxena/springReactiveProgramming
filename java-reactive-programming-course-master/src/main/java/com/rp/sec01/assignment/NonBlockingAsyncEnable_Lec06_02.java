package com.rp.sec01.assignment;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class NonBlockingAsyncEnable_Lec06_02 {
    public static void main(String[] args) {

        /*Very imp concept is tackled here > Async and non-blocking. In previous Lec06 we saw default behavior where execution is happening on the main thread. Here we have
        enabled async behavior by adding 'subscribeOn' in the reactive stream or chain*/
        getName(20);
        IntStream.range(0,10).forEach(i -> getName(i).subscribeOn(Schedulers.boundedElastic()).subscribe((str) -> System.out.println("Full Name is >> " + str)));
        getName(30);
        /*sleep added to complete the execution else pipeline will not be executed. This we won't have to do when we will deploy on to server as server main thread will
        keep on running*/
        threadSleep(100);

    }

    public static Mono<String> getName(int i){
        System.out.println("Enterd into the method > " + i);
        return Mono.fromSupplier(() -> {
            System.out.println("Entered into pipeline execution");
            threadSleep(3);
            return Faker.instance().name().fullName() + " " + i;
        });
    }

    public static void threadSleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
