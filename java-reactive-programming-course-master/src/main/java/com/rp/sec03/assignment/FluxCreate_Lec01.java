package com.rp.sec03.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class FluxCreate_Lec01 {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.next(3);
            fluxSink.complete();
        }).subscribe(i -> System.out.println("Received >> "+ i),
                ex -> System.out.println(ex),
        () -> System.out.println("completed"));


        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.next(3);
            fluxSink.complete();
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("Received >> "+ o);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });
    }
}
