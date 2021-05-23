package com.rp.sec03.assignment;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateAssignment_Lec06 {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            String country = Faker.instance().country().name();
            if(!country.equalsIgnoreCase("canada")){
                synchronousSink.next(country);
            }else{
                System.out.println("Last Country :: " + country);
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
