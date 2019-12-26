package com.example.java8test.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author: GuanBin
 * @date: Created in 下午9:56 2019/12/15
 */
public class StreamTest {
    public static void main(String[] args) {
        Path path = Paths.get("./Main.java");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
