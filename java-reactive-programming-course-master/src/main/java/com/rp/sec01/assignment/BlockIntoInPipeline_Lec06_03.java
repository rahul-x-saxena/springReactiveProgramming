package com.rp.sec01.assignment;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class BlockIntoInPipeline_Lec06_03 {
    public static void main(String[] args) {

        getName(20);
        IntStream.range(0,10).forEach(i -> getName(i).subscribeOn(Schedulers.boundedElastic()).block());
        getName(30);

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
