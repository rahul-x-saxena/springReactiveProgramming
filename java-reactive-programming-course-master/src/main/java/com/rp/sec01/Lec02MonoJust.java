package com.rp.sec01;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class Lec02MonoJust {

    public static void main(String[] args) {

        // publisher
        Mono<Integer> mono = Mono.just(1);
        Flux<Integer> flux = Flux.just(1);

        System.out.println(mono);
        mono.subscribe(i -> System.out.println("Received : " + i));
        mono.subscribe(System.out::println);

        // Just to see how thenMany works for mono and flux publishers.
        mono.thenMany(Flux.just(0,1,2,new Throwable()))
                .log()
                .subscribe(System.out::println);

        flux.thenMany(Flux.just(5,6,7,new Throwable("new Error")))
                .log()
                .subscribe(System.out::println);

    }

}
