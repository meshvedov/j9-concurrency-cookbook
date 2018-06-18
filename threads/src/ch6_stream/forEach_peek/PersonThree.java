package ch6_stream.forEach_peek;

import ch6_stream.creating_streams.Person;

import java.util.Comparator;

public class PersonThree extends Person {

    private static Comparator<Person> comparator = Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName);

    @Override
    public int compareTo(Person otherPerson) {
        return comparator.compare(this, otherPerson);
    }

    @Override
    public boolean equals(Object obj) {
        return this.compareTo((Person) obj) == 0;
    }

    @Override
    public int hashCode() {
        String sequence = this.getLastName() + this.getFirstName();
        return sequence.hashCode();
    }
}
