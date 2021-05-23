package com.rp.sec01.assignment;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileServiceAttempt1 {

    private static final Path PATH = Paths.get("src/main/resources/assignment/sec01");

    public static void main(String[] args) {
        FileServiceAttempt1 fileServiceAttempt1 = new FileServiceAttempt1();

        fileServiceAttempt1.writeFile("file010.txt" , "My name is Rahul").subscribe(
                        (str) -> System.out.println("Value written in file is >> " + str),
                        (ex) -> System.out.println("Exception occured while writing the file >> " + ex),
                        () -> System.out.println("write Operation Completed"));

        fileServiceAttempt1.readFile("file010.txt")
            .subscribe(
                    (obj) -> System.out.println("Value written in file is >> " + (String) obj),
                    (ex) -> System.out.println("Exception occured while reading the file >> " + ex),
                     () -> System.out.println("Read Operation Completed"));

        fileServiceAttempt1.deleteFile("file010.txt")
                .subscribe(
                        (str) -> System.out.println("File is delete"),
                        (ex) -> System.out.println("Exception occured while deleting the file >> " + ex),
                        () -> System.out.println("Delete Operation Completed"));
    }

    public Mono<Object> readFile(String fileName){
        return Mono.fromSupplier(() -> readData(fileName));
    }

    public Mono<String> writeFile(String fileName , String content){
        return Mono.fromRunnable(() -> writeData(fileName , content));
    }

    public Mono<String> deleteFile(String fileName){
        return Mono.fromRunnable(() -> delete(fileName));
    }

    private void delete(String fileName){
        try {
            System.out.println("into delete()");
            Files.delete(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeData(String fileName, String content){
        try {
            System.out.println("into writeData");
            Files.write(PATH.resolve(fileName), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readData(String fileName) {
        String result = null;
        try {
            System.out.println("into readData()");
            Optional<String> optionalResult = Files.readAllLines(PATH.resolve(fileName)).stream().reduce((a, b) -> a.concat(b));
            if(optionalResult.isPresent()){
                result = optionalResult.get();
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
