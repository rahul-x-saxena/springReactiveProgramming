package com.rp.sec02.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class StockPricePublisher_Attempt1 {

    public static void main(String[] args) {

        StockPricePublisher_Attempt1 obj = new StockPricePublisher_Attempt1();
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        //Instead to maintain the count we can also use CountDownLatch
        final Long[] count = {new Long(1)};

        obj.getStockUpdates().subscribeWith(new Subscriber<Long>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("Received Sub : " + subscription);
                atomicReference.set(subscription);
            }

            @Override
            public void onNext(Long l) {
                if(l>90 && l<110) {
                    System.out.println("Price : " + l);
                }else{
                    count[0] = 0L;
                    System.out.println("Price out of range: " + l);
                    atomicReference.get().cancel();

                }

            }

            @Override
            public void onError(Throwable throwable) {
                count[0] = 0L;
                System.out.println("onError : " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                count[0] = 0L;
                System.out.println("onComplete");
            }
        });

        while(count[0] == 1){
            atomicReference.get().request(1);
        }

    }

    public Flux<Long> getStockUpdates(){
        return Flux.interval(Duration.ofSeconds(1)).map(i -> new Long(ThreadLocalRandom.current().nextInt(90, 111)));
    }
}
