package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec05MonoFromSupplier {

    public static void main(String[] args) {

/*
    Use just only when you have data already.
    Remember Mono.just is not lazy operation, it's an eager operation.
    Mono<String> mono = Mono.just(getName());
    Callable and Supplier are functional interface with same SAM
    (Single Abstract Method).
 */

        Supplier<String> stringSupplier = () -> getName();
        Mono<String> mono = Mono.fromSupplier(stringSupplier);
        mono.subscribe(
                Util.onNext()
        );


        Callable<String> stringCallable = () -> getName();
        Mono.fromCallable(stringCallable)
                .subscribe(
                        Util.onNext()
        );
    }

    private static String getName(){
        System.out.println("Generating name..");
        return Util.faker().name().fullName();
    }



}
