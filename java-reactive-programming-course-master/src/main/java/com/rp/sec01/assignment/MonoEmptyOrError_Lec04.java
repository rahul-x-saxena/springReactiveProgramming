package com.rp.sec01.assignment;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;

public class MonoEmptyOrError_Lec04 {
    /*Hit the user repository and get the records (use faker as we are not hitting any db here). Handle the exceptions in case we
    don't have data*/

    public static void main(String[] args) {
        MonoEmptyOrError_Lec04.getRecord(20)
                .subscribe(
                           val -> System.out.println("first name is > " + val),
                           ex -> System.out.println("Exception occured > " + ex.getMessage()),
                           () -> System.out.println("Stream completed"));
    }

    private static Mono<String> getRecord(int userId){

        if(userId == 1){
            return Mono.just(Faker.instance().name().firstName());
        }else if(userId ==2){
            //instead of returning null we should return empty. Always remember Mono is publisher so it should not return null
            return Mono.empty();
        }else {
            return Mono.error(new RuntimeException("Records are not in allowed range"));
        }
    }
}
