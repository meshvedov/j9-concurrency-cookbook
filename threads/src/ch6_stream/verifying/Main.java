package ch6_stream.verifying;

import ch6_stream.creating_streams.Person;
import ch6_stream.forEach_peek.PersonThreeGenerator;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = PersonThreeGenerator.generatePersoneList(10);
        int maxSalary = persons.parallelStream().map(person -> person.getSalary()).max(Integer::compare).get();
        int minSalary = persons.parallelStream().mapToInt(p -> p.getSalary()).min().getAsInt();
        System.out.printf("Salaries are between %d and %d\n", minSalary, maxSalary);

        boolean condition;
        condition = persons.parallelStream().allMatch(p -> p.getSalary() > 0);
        System.out.printf("Salary > 0: %b\n", condition);

        condition = persons.parallelStream().allMatch(p -> p.getSalary() > 10000);
        System.out.printf("Salary > 10_000: %b\n", condition);
        condition = persons.parallelStream().allMatch(p -> p.getSalary() > 30000);
        System.out.printf("Salary > 30_000: %b\n", condition);

        condition = persons.parallelStream().anyMatch(person -> person.getSalary() > 50_000);
        System.out.printf("Any with salary > 50_000: %b\n", condition);

        condition = persons.parallelStream().noneMatch(person -> person.getSalary() > 100_000);
        System.out.printf("None with salary > 100_000: %b\n", condition);

        Person person = persons.parallelStream().findAny().get();
        System.out.printf("Any: %s %s: %d\n", person.getFirstName(), person.getLastName(), person.getSalary());

        person = persons.parallelStream().findFirst().get();
        System.out.printf("First: %s %s: %d\n", person.getFirstName(), person.getLastName(), person.getSalary());

        person = persons.parallelStream().sorted(Comparator.comparingInt(Person::getSalary)).findFirst().get();

        System.out.printf("First sorted: %s %s: %d\n", person.getFirstName(), person.getLastName(), person.getSalary());
    }
}
