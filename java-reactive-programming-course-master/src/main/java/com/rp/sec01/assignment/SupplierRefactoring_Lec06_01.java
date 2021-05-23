package com.rp.sec01.assignment;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

public class SupplierRefactoring_Lec06_01 {
    public static void main(String[] args) {
        //pipeline will only build not execute

        IntStream.range(0,100).forEach(i -> getName(i));
        getName(1000);

        //when subscribe pipeline will be executed
        IntStream.range(0,100).forEach(i -> getName(i).subscribe((str) -> System.out.println("Full Name is >> " + str)));

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
