package com.rp.sec01;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {

    public static void main(String[] args) {
        /*
            We can call subscribe in multiple ways. Here are 2 ways to use subscribe()
         */
        userRepository(20)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );

        userRepository(20)
                .subscribe(new DefaultSubscriber());

    }


    private static Mono<String> userRepository(int userId){
        // 1
        if(userId == 1){
            return Mono.just(Util.faker().name().firstName());
        }else if(userId == 2){
            return Mono.empty(); // null
        }else
            return Mono.error(new RuntimeException("Not in the allowed range"));
    }

}
