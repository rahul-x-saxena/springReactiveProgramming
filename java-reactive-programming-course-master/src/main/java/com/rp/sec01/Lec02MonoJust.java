package com.rp.sec01;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class Lec02MonoJust {

    public static void main(String[] args) {

        // publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        mono.subscribe(i -> System.out.println("Received : " + i));
        mono.subscribe(System.out::println);

    }

}
