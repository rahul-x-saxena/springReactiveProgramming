package com.rp.sec04.assignment;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class HandleAssignment_Lec02 {
    public static void main(String[] args) {
        viaGenerate();
    }

    private static void viaRange(){
        Flux.range(1,100000000)
                .handle(((integer, synchronousSink) -> {
                    String country = Faker.instance().country().name();
                    synchronousSink.next(country);
                    if(country.equalsIgnoreCase("canada"))
                        synchronousSink.complete();
                })).subscribe(Util.subscriber());
    }

    private static void viaGenerate(){
        Flux.generate(synchronousSink -> {
            synchronousSink.next(Faker.instance().country().name());
        }).handle((object , synchronousSink) -> {
            synchronousSink.next(object);
            if(((String) object).equalsIgnoreCase("canada")) //String Typecast can be removed with introduction of map in the reactive streamline
                synchronousSink.complete();
        }).subscribe(Util.subscriber());
    }
}
