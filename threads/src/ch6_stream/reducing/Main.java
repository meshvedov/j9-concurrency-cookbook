package ch6_stream.reducing;

import ch6_stream.creating_streams.Person;
import ch6_stream.creating_streams.PersonGenerator;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        List<Double> numbers = DoubleGenerator.generateDouble(10000, 1000);
        DoubleStream doubleStream = DoubleGenerator.generateStreamFromList(numbers);
        long numberOfElements = doubleStream.parallel().count();
        System.out.printf("The list of number has %d elements.\n", numberOfElements);

        doubleStream = DoubleGenerator.generateStreamFromList(numbers);
        double sum = doubleStream.parallel().sum();
        System.out.printf("Its numbers sum %f.\n", sum);

        doubleStream = DoubleGenerator.generateStreamFromList(numbers);
        double average = doubleStream.parallel().average().getAsDouble();
        System.out.printf("Its numbers have an average value of %f.\n", average);

        doubleStream = DoubleGenerator.generateStreamFromList(numbers);
        double max = doubleStream.parallel().max().getAsDouble();
        System.out.printf("The maximum value in the list is %f.\n", max);

        doubleStream = DoubleGenerator.generateStreamFromList(numbers);
        double min = doubleStream.parallel().min().getAsDouble();
        System.out.printf("The minimum value in the list is %f.\n", min);
        System.out.printf("**********************************************************\n");

        List<Point> points = PointGenerator.generatePointList(10_000);
        Optional<Point> point = points.parallelStream().reduce((point1, point2) -> {
            Point p = new Point();
            p.setX(point1.getX() + point2.getX());
            p.setY(point1.getY() + point2.getY());
            return p;
        });
        System.out.printf(point.get().getX() + " : " + point.get().getY() + "\n");

        System.out.printf("Reduce, second version\n");
        List<Person> persons = PersonGenerator.generatePersoneList(10_000);
        long totalSalary = persons.parallelStream().map(p -> p.getSalary()).reduce(0, (s1, s2) -> s1 + s2);
        System.out.printf("Total salary: %d\n", totalSalary);

        Integer value = 0;
        value = persons.parallelStream().reduce(value, (n,p) -> {
            if (p.getSalary() > 5000) {
                return n + 1;
            } else
                return n;
        }, (n1,n2) -> n1 + n2);

        System.out.printf("The number of people with a salary bigger that 50,000 is %d\n", value);
    }
}
