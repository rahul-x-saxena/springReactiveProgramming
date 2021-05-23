package com.rp.sec01;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec06SupplierRefactoring {

    public static void main(String[] args) {

        getName();
/*
If we were using subscribe directly to the publisher then everything
was getting executed within the main thread and hence we were not able to
see the async behavior.
subscribeOn(Schedulers.boundedElastic()) statement is to introduce Async
behavior.
 */
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(new DefaultSubscriber());
        getName();
        Util.sleepSeconds(4);

/*
Now instead of using subscribe() we can use block() as well so basically it
blocks the main thread. Async behavior will be lost while using block()
hence getName() at line 37 will be executed in the last
 */

        String name = getName()
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        System.out.println("Name with block() " + name);

        getName();
    }

    private static Mono<String> getName(){
        System.out.println("entered getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name..");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }


}
