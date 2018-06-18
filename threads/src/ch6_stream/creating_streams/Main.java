package ch6_stream.creating_streams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.printf("From a Collection:\n");
        List<Person> persons = PersonGenerator.generatePersoneList(10_000);
        Stream<Person> personStream = persons.parallelStream();
        System.out.printf("Number of persons: %d\n", personStream.count());

        System.out.printf("From a Supplier:\n");
        Supplier<String> supplier = new MySupplier();
        Stream<String> generatorStream = Stream.generate(supplier);
        generatorStream.parallel().limit(10).forEach(s -> System.out.printf("%s\n", s));

        System.out.printf("From predefined set of elements:\n");
        Stream<String> elementsStream = Stream.of("Peter", "John", "Mary");
        elementsStream.parallel().forEach(s -> System.out.printf("%s\n",s));

        System.out.printf("From a File:\n");
        try (BufferedReader br = new BufferedReader(new FileReader("/home/mic/BooksCode/concurrency_cookbook/threads/data/nursery.data"))) {
            Stream<String> fileLines = br.lines();
            System.out.printf("Number of lines in the file: %d\n\n", fileLines.parallel().count());
            System.out.printf("***********************************************************\n");
            System.out.printf("\n");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.printf("From a Directory:\n");
            Stream<Path> directoryContent = Files.list(Paths.get(System.getProperty("user.home")));
            System.out.printf("Number of elements (files and folders): %d\n\n", directoryContent.parallel().count());
            directoryContent.close();
            System.out.printf("*******************************************\n");
            System.out.printf("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("From an Array:\n");
        String[] array = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Stream<String> streamFromArray = Arrays.stream(array);
        streamFromArray.parallel().forEach(s -> System.out.printf("%s : ", s));

        Random random = new Random();
        DoubleStream doubleStream = random.doubles(10);
        double doubleStreamAverage = doubleStream.parallel().peek(value -> System.out.printf("%f : ", value)).average().getAsDouble();


        System.out.printf("Concatenating streams:\n");
        Stream<String> stream1 = Stream.of("1", "2", "3", "4", "5");
        Stream<String> stream2 = Stream.of("6", "7", "8", "9", "10");
        Stream<String> finalStream = Stream.concat(stream1, stream2);
        finalStream.parallel().forEach(s -> System.out.printf("%s : ", s));
    }
}
