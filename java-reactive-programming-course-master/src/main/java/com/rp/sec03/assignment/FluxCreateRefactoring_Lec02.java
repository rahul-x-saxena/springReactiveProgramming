package com.rp.sec03.assignment;

import com.rp.courseutil.Util;
import com.rp.sec03.helper.NameProducer;
import reactor.core.publisher.Flux;

public class FluxCreateRefactoring_Lec02 {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(Util.subscriber());

        /*We can do the same with lambda function, but for clarity
        did with old way*/

        Runnable myRunnable =
                new Runnable(){
                    public void run(){
                        nameProducer.produce();
                    }
                };
        for (int i = 0; i < 10; i++) {
            new Thread(myRunnable).start();
        }

        Util.sleepSeconds(2);
    }
}
