package ch6_stream.forEach_peek;

import ch6_stream.creating_streams.Person;
import ch6_stream.reducing.DoubleGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = PersonThreeGenerator.generatePersoneList(10);

        persons.forEach(p -> {
            System.out.printf("%s, %s\n", p.getLastName(), p.getFirstName());
        });

        List<Double> doubles = DoubleGenerator.generateDouble(10, 100);
        System.out.printf("Parallel forEachOrdered() with numbers\n");
        doubles.parallelStream().sorted().forEachOrdered(n -> System.out.printf("%f\n", n));

        System.out.printf("Parallel forEach() after sorted() with numbers\n");
        doubles.parallelStream().sorted().forEach(n -> {
            System.out.printf("%f\n", n);
        });

        persons.parallelStream().sorted().forEachOrdered(p -> System.out.printf("%s, %s\n", p.getLastName(), p.getFirstName()));

        doubles.parallelStream()
                .peek(d -> System.out.printf("Step 1: Number: %f\n",d))
                .peek(d -> System.out.printf("Step 2: Number: %f\n",d))
                .forEach(d -> System.out.printf("Final Step: Number: %f\n", d));
    }
}
