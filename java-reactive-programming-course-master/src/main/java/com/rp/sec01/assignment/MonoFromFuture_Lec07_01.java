package com.rp.sec01.assignment;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture_Lec07_01 {
    public static void main(String[] args) {
        Mono.fromFuture(getName())
                .subscribe((str) -> System.out.println("Full name is >> " + str));
        /*We added this because Future is async task so main thread will continue and will exit and the other thread
        which is working for CompletableFuture will not be able to complete the task before main thread exit
        So basically we are blocking main thread to see the result*/
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> Faker.instance().name().fullName());
    }


}
