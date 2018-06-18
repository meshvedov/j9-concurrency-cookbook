package ch6_stream.sorting;

import ch6_stream.creating_streams.Person;
import ch6_stream.creating_streams.PersonGenerator;
import ch6_stream.forEach_peek.PersonThreeGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] numbers={9,8,7,6,5,4,3,2,1,2,3,4,5,6,7,8,9};
        Arrays.stream(numbers).parallel().sorted().forEachOrdered(n -> System.out.printf("%d ", n));

        List<Person> persons = PersonThreeGenerator.generatePersoneList(10);
        persons.parallelStream().sorted().forEachOrdered(p -> System.out.printf("%s, %s\n", p.getLastName(), p.getFirstName()));
        System.out.println("11111111111111111111111111\n");

        TreeSet<Person> personTreeSet = new TreeSet<>(persons);
        for (int i = 0; i < 10; i++) {
            Person person = personTreeSet.stream().parallel().limit(1).collect(Collectors.toList()).get(0);
            System.out.printf("%s %s\n", person.getFirstName(), person.getLastName());
            System.out.println("1**************");
            person = personTreeSet.stream().unordered().parallel().limit(1).collect(Collectors.toList()).get(0);
            System.out.printf("%s %s\n", person.getFirstName(), person.getLastName());
            System.out.println("2**************");
        }
    }
}
