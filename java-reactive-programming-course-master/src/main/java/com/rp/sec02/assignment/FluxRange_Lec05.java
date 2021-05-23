package com.rp.sec02.assignment;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxRange_Lec05 {

    public static void main(String[] args) {
        Flux.range(1,10).
                map((i) -> Faker.instance().name().firstName()).
                subscribe(Util.onNext(),
                          Util.onError(),
                          Util.onComplete());
    }
}
