package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Lec03FluxFromArrayOrList {

    public static void main(String[] args) {

        /*List interface extends Collection and Collection extends Iterable interface.
         Hence we can pass Collection objects in fromIterable method.*/
        Iterable<String> stringsIterable = Arrays.asList("z", "y", "x");
        Flux.fromIterable(stringsIterable)
                .subscribe(Util.onNext());

        List<String> strings = Arrays.asList("a", "b", "c");
        Flux.fromIterable(strings)
                .subscribe(Util.onNext());

         Integer[] arr = { 2, 5, 7, 8};
        Flux.fromArray(arr)
                .subscribe(Util.onNext());


    }

}
