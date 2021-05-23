package com.rp.sec02.assignment;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FluxFromStream_Lec04 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Stream<Integer> stream = list.stream();

        // stream.forEach(System.out::println); // closed
        // stream.forEach(System.out::println);

        Flux.fromStream(list.stream()).subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

        Flux.fromStream(list.stream()).subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());


        Flux<Integer> integerFlux = Flux.fromStream(() -> list.stream());

        integerFlux.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());

        integerFlux.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());


    }
}
