package com.rp.sec03.assignment;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReaderService_Attempt1 {

    private static final Path PATH = Paths.get("src/main/resources/assignment/sec03");

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            FileReaderService_Attempt1 obj = new FileReaderService_Attempt1();
            if(!fluxSink.isCancelled())
                fluxSink.next(obj.readData("file01.txt",10l));
        })
                .subscribe((stream) -> {
                    Stream<String> abc = (Stream<String>)stream;
                    abc.forEach(System.out::println);
                }, (ex) -> System.out.println("Exception >> " + ex.getMessage()),
                        () -> System.out.println("Operation completed"));
    }

    private Stream<String> readData(String fileName,long limitLines) {
        try {
            return Files.lines(PATH.resolve(fileName)).limit(limitLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
